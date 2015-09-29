# -*- coding: UTF-8 -*-

import sys
import urllib
from urlparse import urlparse
from urlparse import urljoin
import socket
import re


failed_pages = {}
all_pages = {}
stack = []
startedNetloc = ''


def have_protocol( url ):
    parsedUrl = urlparse(url)
    if (parsedUrl.scheme in ['https', 'http', 'ftp']):
        return True
    return False


def check_URL():
    while (len(stack) > 0):
        url = stack.pop()
        try:
            if (not (urlparse(url).netloc == startedNetloc)):
                break
            if (url in all_pages.keys()):
                break
            html = urllib.urlopen(url)
            data = html.read()
            code = html.getcode()
            all_pages[url] = code
            if (code in [200, 301]):
                urls = []
                urls.extend(re.findall('<a href="(.+?)".+?>', data, re.DOTALL))
                for tempUrl in urls:
                    if (not have_protocol(tempUrl)):
                        tempUrl = urljoin(url, tempUrl)
                    if (tempUrl not in all_pages.keys()):
                        print tempUrl
                        stack.append(tempUrl)
            else:
                failed_pages[url] = code
        except socket.error, e:
            failed_pages[url] = 'Ping Error'
    return


def perform_output():
    f = open('report.txt', 'w')
    for key in all_pages.keys():
        f.write('{0} - {1}\n'.format(key, all_pages[key]))
    f.write('\n')
    for key in failed_pages.keys():
        f.write('{0} - {1}\n'.format(key, failed_pages[key]))


if len(sys.argv) < 2:
    exit()
if (not have_protocol(sys.argv[1])):
    print ('Expected absolute link')
    exit()
startedNetloc = urlparse(sys.argv[1]).netloc
print (startedNetloc)
stack.append(sys.argv[1])
check_URL()
perform_output()
#ошибка в нетлоце который != имени домена
