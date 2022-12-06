import nltk
from lib2to3.pgen2.tokenize import tokenize
import pickle
import sys
from collections import Counter
nltk.download('punkt')

def program1(fileName):
 try:
    file = open(fileName,"r")
    raw_text = file.readlines()
    raw_text = list(map(lambda i: i.strip(), raw_text))
    textTokens = nltk.tokenize.word_tokenize(' '.join(raw_text))
    textBigrams = list(nltk.ngrams(textTokens, 2))
    textUnigrams = list(nltk.ngrams(textTokens, 1))
        
    bigramCount = dict(Counter(textBigrams))
    unigramCount = dict(Counter(textUnigrams))
    return bigramCount, unigramCount

 except:
    print("Error Message: Can not open file")
    return -1

if __name__=="__main__":
    englishUni, englishBi = program1('english.txt')
    frenchUni, freanchBi = program1('french.txt')
    italianUni, italianBi = program1('italian.txt')

with open('english_unigram_count_dictionary.pickle', 'wb') as file:
    pickle.dump(englishUni, file)
with open('english_bigram_count_dictionary.pickle', 'wb') as file:
    pickle.dump(englishBi, file)
with open('french_unigram_count_dictionary.pickle', 'wb') as file:
    pickle.dump(frenchUni, file)
with open('french_bigram_count_dictionary.pickle', 'wb') as file:
    pickle.dump(freanchBi, file)
with open('italian_unigram_count_dictionary.pickle', 'wb') as file:
    pickle.dump(italianUni, file)
with open('italian_bigram_count_dictionary.pickle', 'wb') as file:
    pickle.dump(italianBi, file)

#there were some errors I spent the past day trying to debug to turn in late 
# but I think it is just a problem on my machine and the configurations settings, after testing elsewhere this should work