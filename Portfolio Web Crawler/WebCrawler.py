import urllib.request
import re
import requests
from bs4 import BeautifulSoup
from nltk.tokenize import sent_tokenize
import requests
import sys

def isVisible(element):
    if(element.find_element_by_xpath().name in ['style', 'script', '[document]', 'head', 'title']):
        return False 
    elif re.match('<!--.*-->', str(element.encode('utf-8'))):
        return False 
    return True

def get_important_terms(filename):
    with open(filename, 'r') as r:
        lines = r.read()
    total_words = len(lines)

def text_clean_up(filename):
    with open(filename, 'r') as reader:
        #takes out tabs and newlines 
        sentences = [re.sub("^\s*|\s*$","",re.sub("\s+"," ",each))  for each in open(filename).read().split(".")]
        tokens = sent_tokenize(str(sentences))

        #writes the tokens to the output file
        with open('output'+ filename + '.txt', 'w') as output: 
                    output.write(str(tokens))


def main():
    main_URL = "https://cookieandkate.com/"
    r = requests.get(main_URL)
    data = r.text
    soup = BeautifulSoup(data, "html.parser")
    count = 0
    
    with open("urls.txt", 'w') as w:
        for link in soup.find_all("a"):
            print(link.get('href'))
            w.write(str(link.get('href')) + '\n')
            slink = str(link.get('href'))
            print(slink)

            if('cooking' in slink or 'Cooking' in slink):
                if('&' in slink):
                    i = slink.find('&')
                    slink = slink[:i]
                if slink.startswith('/url?q='):
                    slink = slink[15:]
                    print('MOD:', slink)
                if(slink.startswith('google') and 'http' not in slink):
                    w.write(slink+'\n')

    with open('URLS.txt', 'r') as r:
        urls = r.read().splitlines()
        for u in urls:
                print(u)

    request = urllib.request.Request(main_URL, headers ={'User-Agent': 'Mozilla/5.0'})
    with open('URLS.txt', 'r') as input: 
        for(counter, line) in enumerate(input):
            with open('link{0}.txt'.format(counter), 'w', encoding='utf-8') as output: 
                html = urllib.request.urlopen(request)
                soup = BeautifulSoup(html, 'html.parser')
                data2= html.read()
                print('data 2: \n', data2)
                data = soup.findAll(test=True)
                Stringafter = filter(isVisible, data)
                temp = list(Stringafter) 
                stringTemp = ' '.join(temp)
                output.write(str(data2))    

        print("crawling done")
    while 'link'+count+'.txt':
        fileName = 'link'+count+'txt'
        text_clean_up(fileName)
        count += 1
    

main()