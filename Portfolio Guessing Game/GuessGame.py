from lib2to3.pgen2.tokenize import tokenize
import sys
import random 
import nltk
from nltk.corpus import stopwords
from nltk import WordNetLemmatizer

def printList(listIn):
    for i in listIn:
        print(i, end=" ")

def printCurrentGuess(correctLetter, wordChosen, myList):
    for i in range(len(wordChosen)):
        if(wordChosen[i] == correctLetter):
            myList[i] = correctLetter
    for j in myList:
        print(j, end=" ")

def compareWords(myList, wordChosen):
    found = False
    for i in range(len(myList)):
        if myList[i] == wordChosen[i]:
            found = True
    return found

def getLexDiv(fileName):
    with open(fileName) as reader:
         raw_text = reader.read()
         txt_tokens = nltk.word_tokenize(raw_text)
         text = nltk.Text(txt_tokens)
    numTokens = len(txt_tokens)
    numUniqueTokens = len(set(nltk.word_tokenize(raw_text)))
    lexical_diversity = numUniqueTokens / numTokens
    print(fileName + " lexical divesity : "+ "{:.2f}".format(lexical_diversity))

def preProcessText(fileName):
    lemmatizer = WordNetLemmatizer() 
    with open(fileName) as reader:
        raw_text = reader.read()
        text_tokens = nltk.word_tokenize(raw_text.lower())
        stop_words = set(stopwords.words('english'))
        filtered_text = [w for w in text_tokens if not w.lower() in stop_words if len(w) > 5]
        lemma_count_text = len(set([lemmatizer.lemmatize(w,'v') for w in filtered_text]))
        posTag = nltk.pos_tag(text_tokens)[:20]
    print(filtered_text[:20])
    print("Unique lemmas : ",lemma_count_text)
    print(posTag)


def main():
    filename = 'heartAnatomy.txt'
    try:
        with open(filename) as file:
            contents = file.readlines()
            contents = [word.strip() for word in contents]
            words = []
            for text in contents :
                text = text.split(" ")
            for word in text:
                words.append(word.strip())
    except:
        print("Error Message: Can not open file")
        quit()

    getLexDiv(filename)

    print("Let's play a word guessing game, you will have 5 chances to guess correctly!")
    amountToGuess = 5
    score = 5
    wordChosen = random.choice(words)
    gameRound = 0
    totalGuesses = 0
    gameOver = False
    print(wordChosen) #see the correct word for debugging
    
    while True:
        myList = []
        gameRound += 1
        print("Round", gameRound)
        for i in range(len(wordChosen)):
            myList.append("_ ")
        printList(myList)

        wordFound = False
        for i in range(amountToGuess):
            playerInput = input("\nGuess a letter: ").lower()
            if(playerInput[0] == '!'):
                gameOver = True
                break
            foundLetter = False 
            for j in range(len(wordChosen)):
                if(playerInput == wordChosen[j]):
                    foundLetter = True
                    myList[j] == wordChosen[j]
            if(foundLetter == True):
                score += 1
                print("Correct! The word so far\n")
                printCurrentGuess(playerInput, wordChosen, myList)
                
            else: 
                score -= 1
                print("Sorry, Guess Again!\n")
                #printCurrentGuess(playerInput, wordChosen, myList)

            if compareWords(myList, wordChosen):
                print("\nGood job, you guessed the word " + wordChosen.upper() + " correctly!")
                wordFound = True
                totalGuesses +=1
                break

        if(score <= -1):
            gameOver = True

        if(wordFound == False):
            print("\nYou did not guess the word, the correct word was "+wordChosen.upper()+ ". Play Again!")
        if gameOver:
            break
    print("Total Rounds played", gameRound)
    print("Correct guesses", totalGuesses)
    print("Total Score", score)

main()
