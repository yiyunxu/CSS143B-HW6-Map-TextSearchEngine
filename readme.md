## Page Rank

### Basic Info

    - Assigned: 2/27/2020
    - Due: 3/4/2020 Wednesday 11:59pm

### Submission 

- Put your solutions (all .java) into a folder.
- Zip this folder into a single file and submit through canvas. 
- The naming format is used for grading script and you will receive **ZERO** point if this is not followed.

## Design and Implement a mini text search engine (100pt)

Ready to take on google yet? Well, let's give it a try.

### TL;DR

- write index() and search()
- index(...) takes a list of strings and generate indexes
- search(...) finds single word as well as phrase of words
- must pass provided tests
- do not use string comparison to scan each doc to do search
- suggestive reading/idea [here](http://www.ardendertat.com/2011/05/30/how-to-implement-a-search-engine-part-1-create-index/) and [here](http://www.ardendertat.com/2011/05/31/how-to-implement-a-search-engine-part-2-query-index/). this is also what my solution will be based on

### The Idea

This project will produce a text search engine that can search a single word or phrase made up of multiple words from the given texts. For example, given the following documents:

 - document 0: "hello world"
 - document 1: "hello"
 - document 2: "world"
 - document 3: "world world hello"
 - document 4: "seattle rains hello abc world"
 - document 5: "sunday hello world fun"

Search(...) only works with words. For example, with the above documents, search("llo wor") will return [] even though this string exists in documents 0, and 5.

Each document is represented by a string in the code. 

The search engine works by firstly cleaning up data (removing non-alphabetical character) and generating indexes from all the documents. Indexing collects, parses, and stores data to facilitate fast and accurate search. Once indexes are generate, text keywords can be searched by calling 

This function returns a vector of document Id that contains the searched term. For example:

- search("seattle") --> \[4\]
- search("Seattle") --> \[4\]
- search("hello world") --> \[0, 5\]
- search("bothell") --> \[\]

Here the number in the result vector are document id, 0 to 5 for the documents example above. 

Note that this search engine is not case sensitive. All words in the documents will be converted to lower case and search will be conducted also in lower case. And non-alphabetical character will be removed. Utility functions for these purpose are already provided in the code. 

Also note that a phrase kehyword like "hello world" only matches documents that have this phase as a whole, here 0, and 5. Document 3 has both "hello" and "world" but in the opposite order, so it is not a match. 

### The Cogs

Before you say "just use string comparision with the keyword on each doc", keep in mind that search engines usually deal with very large documents and see lots of repeated searches. String comparison is usually O(N) complexity with N being the size of documnets. However with an indexing (or hashing) based algorithm, the complexity can be largely reduced. The idea is to have a hash table data structure where key is each word in all the docs, and value is a vector of all the documents that contain that word. For example:

- "hello" : \[0, 1, 3, 5\]
- "seattle" : \[4\].

This way, if "hello" is searched, we can quickly return in O(1) time that it appears in document 0, 1, 3, and 5.

With this single layer of structure, it is still hard to search phrase because words in a phrase matters in a search, and therefore the information of where each word appears in documents also need to be kept in the indexes. This part will be designed and implemented by you. [Here](http://www.ardendertat.com/2011/05/30/how-to-implement-a-search-engine-part-1-create-index/) is an example solution if you need some help or inspiration. 

**If each search is done by scanning in all documents by string comparsion, a 50pt penalty will take place plus any test failure. String comparison is only allowed for dictionay key operation.**
