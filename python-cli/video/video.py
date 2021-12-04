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
    

def read(filename, mode):
   video_api_url = "http://localhost:8080/video"
   body = {"filename": filename,"mode" : mode}
   headers =  {"Content-Type":"application/json"}

   response = requests.post(video_api_url, data=json.dumps(body), headers=headers)
   
   return response.json()

if __name__ == '__main__':
    parser = ArgumentParser(description='A command line tool for interacting with our video API')
    print("example: video -filename squid_game_1.1.mkv –mode 4k \n")
    myargs = getopts(argv)
    
    if '-filename' in myargs: 
        if '-mode' in myargs:
            print(read(myargs['-filename'],myargs['-mode']))
        else:
            print("missing mode argument")
    else:
        print("missing file name argument")