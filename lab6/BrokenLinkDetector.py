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
started_domen = ''
MAX_DEPTH = 300


def have_protocol(url):
    parsed_url = urlparse(url)
    if parsed_url.scheme in ['https', 'http', 'ftp']:
        return True
    return False


def check_url():
    while len(stack) > 0:
        url = stack.pop()
        print url
        try:
            if get_domen_name(urlparse(url).netloc) != started_domen:
                break
            if url in all_pages.keys():
                break
            try:
                html = urllib.urlopen(url)
            except:
                failed_pages[url] = '401'
                continue
            data = html.read()
            code = html.getcode()
            all_pages[url] = code
            if code in [200, 301]:
                urls = []
                urls.extend(re.findall('<a href="(.+?)".+?>', data, re.DOTALL))
                for temp_url in urls:
                    if temp_url == '/' or temp_url[0] == '"' or temp_url[0] == '{':
                        continue
                    if temp_url == ' /partners/developers/':
                        temp_url = urljoin(url, '/partners/developers/')
                    elif not have_protocol(temp_url):
                        temp_url = urljoin(url, temp_url)
                    if get_domen_name(urlparse(temp_url).netloc) == started_domen:
                        if temp_url not in all_pages.keys():
                            stack.append(temp_url)
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


def get_domen_name(netloc):
    first = netloc[:netloc.find('.')]
    domen = netloc[netloc.find('.') + 1: len(netloc)]
    if domen.find('.') == -1:
        return netloc
    else:
        return domen
    

if len(sys.argv) < 2:
    exit()
if not have_protocol(sys.argv[1]):
    print ('Expected absolute link')
    exit()
started_domen = get_domen_name(urlparse(sys.argv[1]).netloc)
stack.append(sys.argv[1])
check_url()
perform_output()
