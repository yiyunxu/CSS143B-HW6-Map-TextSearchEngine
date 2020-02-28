## Page Rank

### Basic Info

    - Assigned: 2/27/2020
    - Due: 3/4/2020 Wednesday 11:59pm

### Work and Submission 

- Check out this repo to your own repo (much like what you did for the quiz)
- Make changes and commits as needed
- Once done, send the repo url through canvas
- DO NOT MAKE UPDATE TO YOUR REPO **AFTER** DUE DATE OR ZERO POINT IS GIVEN

## Design and Implement a mini text search engine (100pt)

Ready to take on google yet? Well, let's give it a try.

### TL;DR

- write index(List<String> documents) and search(String phrase)
- index(List<String> documents) takes a list of strings (each string called a document) and generates indexes
- search(String phrase) returns which documents (document id) the phrase appears in. Phrase can contain more than one words.
- search(String phrase) only works with full words
- this search engine ignore cases
- do not use string comparison to scan each doc to do search
- suggestive reading/idea [here](http://www.ardendertat.com/2011/05/30/how-to-implement-a-search-engine-part-1-create-index/) and [here](http://www.ardendertat.com/2011/05/31/how-to-implement-a-search-engine-part-2-query-index/). this is also what my solution will be based on.

### The Idea

This project will produce a text search engine that can search a single word or phrase made up of multiple words from the given texts. For example, given the following documents:

 - document 0: "hello world"
 - document 1: "hello"
 - document 2: "world"
 - document 3: "world world hello"
 - document 4: "seattle rains hello abc world"
 - document 5: "sunday hello world fun"

search() works with a phrase of words, and it will only match **full words**. Also as a reasonable simplification, this search engine is **case insensitive**.

```java
    public List<Integer> search(String keyPhrase) {
        // homework
        return new ArrayList<>(); // place holder
    }
````

For example, with the above documents, search("llo wor") will return [] even though this string exists in documents 0, and 5.

Each document is represented by a string in the code. 

The search engine works by firstly generating indexes from given documents. Indexing collects, parses, and stores data to facilitate fast search. Once indexes are generate, text keywords can be searched by calling 

```java
search(String keyPhrase)
```

This function returns a vector of document Id that contains the searched term. For example:

- search("seattle") --> \[4\]
- search("Seattle") --> \[4\]
- search("hello world") --> \[0, 5\]
- search("bothell") --> \[\]

Here the number in the result list are document id, 0 to 5 for the documents example above. 

Because this search engine is not case sensitive, all words in the documents will be converted to lower case and search will be conducted also in lower case. 

Also note that a phrase kehyword like "hello world" only matches documents that have this phase as a phrase, not by single word, e.g. here documents 0, and 5. Document 3 contains both "hello" and "world" but in the opposite order, so it is not a match. 

### The Cogs

Before you say "just use string comparision with the keyword on each doc", keep in mind that search engines usually deal with very large documents and see lots of repeated searches. String comparison is usually O(N) complexity with N being the size of documnets. However with an indexing (or hashing) based algorithm, the complexity can be largely reduced. The idea is to have a hash table data structure where key is each word in all the docs, and value is a vector of all the documents that contain that word. For example:

- "hello" : \[0, 1, 3, 5\]
- "seattle" : \[4\].

This way, if "hello" is searched, we can quickly return in O(1) time that it appears in document 0, 1, 3, and 5.

With this single layer of structure, it is still hard to search phrase because words in a phrase matters in a search, and therefore the information of where each word appears in documents also need to be kept in the indexes. This part will be designed and implemented by you. [Here](http://www.ardendertat.com/2011/05/30/how-to-implement-a-search-engine-part-1-create-index/) is an example solution if you need some help or inspiration. 

**If each search is done by scanning in all documents by string comparsion, a 50pt penalty will take place plus any test failure. String comparison is only allowed for dictionay key operation.**
