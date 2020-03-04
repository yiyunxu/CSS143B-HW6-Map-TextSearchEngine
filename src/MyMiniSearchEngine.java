import java.util.*;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    private Map<String, List<List<Integer>>> indexes;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {
        //homework
        indexes = new HashMap<>();
        for (int i = 0; i< texts.size(); i++) // loop document number
        {
            String[] words = texts.get(i).split(" ");
            for (int j = 0; j< words.length; j++) // loop word index
            {
                String key = words[j].toLowerCase();
                //System.out.println(key);
                if (indexes.containsKey(key))
                {
                    List<List<Integer>> value = indexes.get(key);
                    value.get(i).add(j);
                    indexes.replace(key, value);
                    //System.out.println(key + " " + value);
                }
                else {
                    List<List<Integer>> value = new ArrayList<>();
                    // set up total number of documents
                    for (int k = 0; k < texts.size(); k++)
                    {
                        value.add(new ArrayList<Integer>());
                    }
                    value.get(i).add(j);
                    indexes.put(key, value);
                    //System.out.println(key + " " + value);
                }
            }
        }
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        String[] words = keyPhrase.split(" ");
        List<List<Integer>> documentID = new ArrayList<>();

        // look for document numbers, return 2D list of doc numbers, example [[1],[1,2],[1,2,3]]
        for (int i = 0; i< words.length; i++) // loop through words
        {
            String key = words[i].toLowerCase();
            documentID.add(new ArrayList<>());
            if (indexes.containsKey(key)) {
                List<List<Integer>> value = indexes.get(key);

                for (int j = 0; j < value.size(); j++) { // loop through documents
                    if (!value.get(j).isEmpty())
                        documentID.get(i).add(j);
                }
            }
        }

        // single word search
        if (documentID.size() < 2)
            return documentID.get(0); // if not found, returns empty list

        // multi words search
        else {
            List<Integer> intersectDocs = intersectLists(documentID);
            List<Integer> results = new ArrayList<>();

            for (int document : intersectDocs) { // loop through documents
                List<List<Integer>> searchIndex = new ArrayList<>();
                for (String word : words) {
                    searchIndex.add(indexes.get(word.toLowerCase()).get(document));
                }

                // subtract -ith from the key phrase
                List<List<Integer>> subtractIndex = subtractWordIndex(searchIndex);
                List<Integer> intersectIndex = intersectLists(subtractIndex);

                if (!intersectIndex.isEmpty())
                {
                    results.add(document);
                }
            }
            return results;
        }
    }

    private List<Integer> intersectLists(List<List<Integer>> list)
    {
        List<Integer> baseline = new ArrayList(list.get(0));

        for (int i = 1; i < list.size(); i++)
        {
            for (int j = 0; j < list.get(0).size(); j++)
            {
                int num = list.get(0).get(j); // use 0th list as reference
                if (!list.get(i).contains(num)) // remove index if the ith list does not have list.get(0).get(j)
                {
                    baseline.remove(j);
                }
            }
        }
        return baseline;
    }

    private List<List<Integer>> subtractWordIndex(List<List<Integer>> list)
    {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++)
        {
            result.add(new ArrayList<>());
            for (int j = 0; j < list.get(i).size(); j++)
            {
                int subtractI = list.get(i).get(j) - i;
                result.get(i).add(subtractI);
            }
        }
        return result;
    }
}
