# -*- coding: UTF-8 -*-

import sys
import urllib
import socket


failed_pages = {}
all_pages = {}


def check_URL( url ):
    code = urllib.urlopen(url).getcode()
    all_pages[url] = code
    if (code not in [200, 301]):
        failed_pages[url] = code
    return

    

if len(sys.argv) < 2:
    exit()
starting_URL = sys.argv[1]
check_URL(starting_URL)

f = open("report.txt", 'w')
for key in all_pages.keys():
    f.write("{0} - {1}\n".format(key, all_pages[key]))
f.write("\n")
for key in failed_pages.keys():
    f.write("{0} - {1}\n".format(key, failed_pages[key]))
