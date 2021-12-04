import json
import requests
from pprint import pprint
from sys import argv
from argparse import ArgumentParser
import requests
from requests_toolbelt.utils import dump

#get args from cli
def getopts(argv):
    opts = {} 
    while argv:  
        if argv[0][0] == '-':  
            opts[argv[0]] = argv[1]  
        argv = argv[1:]  
    return opts
    

def read(id):
   status_api_url = "http://localhost:8080/api?&id=" + id

   response = requests.get(status_api_url)
   
   return response.json()

if __name__ == '__main__':
    parser = ArgumentParser(description='A command line tool for interacting with our video status API')
    print("example: status -id 37dbe9d2-65ec-487a-b16b-b726bad71156 \n")
    myargs = getopts(argv)
    
    if '-id' in myargs: 
        print(read(myargs['-id']))
    else:
        print("missing id argument")