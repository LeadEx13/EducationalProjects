def tokenization(text):
    '''
    splits the string into list of words
    para:text - The text in string form
    '''
    return text.split()

def removal_stopWords(text_list,stop_list):
    '''
    removes stop words from the list
    para:text_list - list of words
    para:stop_list - a list of words(in string format)
    '''
    return filter(lambda x:(stop_bool(x,stop_list)),text_list)

def stop_bool(word,stop_list=('is', 'it', 'are', 'our', 'my', 'the', 'they', 'and')):
    '''
    tells if the word is a stop word in boolean format(false/true)
    para:word- a word in string format
    para:stop_list - a list of words(in string format)
    '''
    for w in stop_list:
        if word == w or word.lower() == w:
            return False
    return True

def numbers_removal(text_list):
    '''
    removes numbers from the list
    para:text_list - list of words(in string format)
    '''
    return [i for i in text_list if not i.isdigit()]

def stemming(text_list,stem_rules = ('less', 'ship', 'ing', 'es', 'ly','s')):
    '''
    removes the stem if its at the end of a word
    para:text_list - list of words
    para:stem_rules -list of stem rules(in string format)
    '''
    result = (text_list).copy()
    for index,word in enumerate(result):
        for stem in stem_rules:
            if word.endswith(stem):
                result[index] = word[:-(len(stem))]
                break
    return result

def calculating_grams_bi(text_list):
    '''
    returns a list of pairs of any 2 words that are next to each other
    para:text_list - list of words(in string format)
    '''
    result=list()
    for i,word in enumerate(text_list):
        if(i==(len(text_list)-1)):
            break
        result.append((word,text_list[i+1]))
    return result

def getBigrams(text,stop_list=('is', 'it', 'are', 'our', 'my', 'the', 'they', 'and'),stem_rules = ('less', 'ship', 'ing', 'es', 'ly','s')):
    '''
    Take text runs it trough the pipeline that use all the functions above
    para:text_list - list of words(in string format)
    '''
    return calculating_grams_bi( stemming( numbers_removal(removal_stopWords(tokenization(text),stop_list)),stem_rules))

text = 'My 100 friends are very friendly They are keeping our friendship '
print(getBigrams(text))
