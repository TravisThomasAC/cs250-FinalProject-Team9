from random import randint
import pandas as pd
import csv


def melodyRating():
    ratings = []
    df = pd.read_csv("music2.csv")
    totalrows = len(df['SongID'])

    for i in range(totalrows):
        rand = randint(2,10)
        ratings.append(rand)

    return ratings


def createColumn():

    ratings = melodyRating()
    '''
    HEADER = [["Melody"]]
    NEWFILE = open("music2.csv", 'w')
    with NEWFILE:
        writer = csv.writer((NEWFILE),lineterminator='\n')
        #writer.writerows(HEADER)
        '''
    for i in range(len(ratings)):
        data = [[ratings[i]]]
        NEWFILE = open("music2.csv",'w')
        with NEWFILE:
            writer = csv.writer((NEWFILE),lineterminator='\n')
            writer.writerows(data)
    NEWFILE.close()

def merge_df():

    df = pd.read_csv("music.csv")
    df2 = pd.read_csv("music2.csv")

    final_df = pd.DataFrame.join(df,df2)

    writer = pd.ExcelWriter('music3.xlsx', engine='xlsxwriter')

    final_df.to_excel(writer, sheet_name = "music", header = False, index = False )

    writer.save()

def TO_CSV():
    merge_df()
    df = pd.read_excel("music3.xlsx")
    df.to_csv('music.csv', encoding='utf-8', index=False)

TO_CSV()
