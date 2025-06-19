#!/usr/bin/env python3
from bs4 import BeautifulSoup
import requests
import sys
import time
import re

urls = [
    "https://en.wikipedia.org/wiki/2025_Romanian_presidential_election", 
    "https://en.wikipedia.org/wiki/Romania",
    "https://en.wikipedia.org/wiki/2024_Romanian_presidential_election"
]

def clean_text(text):
    """Clean and normalize text content"""
    text = re.sub(r'\s+', ' ', text)
    text = re.sub(r'[^\x20-\x7E\u00A0-\uFFFF]', '', text)
    return text.strip()

def fetch_wikipedia_content():
    """Fetch clean Wikipedia content focusing on main article text"""
    
    for url in urls:
        try:
            print(f"Fetching content from: {url}", file=sys.stderr)
            
            headers = {
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36'
            }
            
            response = requests.get(url, headers=headers, timeout=30)
            response.raise_for_status()
            
            soup = BeautifulSoup(response.content, "html.parser")
            
            for element in soup(['script', 'style', 'nav', 'footer', 'header', 
                               'aside', 'sup', 'sub']):
                element.decompose()
            
            content_areas = []
            
            main_content = soup.find('div', {'id': 'content'})
            if main_content:
                paragraphs = main_content.find_all('p')
                for p in paragraphs:
                    text = p.get_text()
                    if text and len(text) > 50: 
                        clean = clean_text(text)
                        if clean and not any(keyword in clean.lower() for keyword in [
                            'newpp limit', 'cached time', 'parser cache', 'cpu time',
                            'lua time', 'template argument', 'wikibase entities'
                        ]):
                            content_areas.append(clean)

            if not content_areas:
                paragraphs = soup.find_all('p')
                for p in paragraphs:
                    text = p.get_text()
                    if text and len(text) > 20:
                        clean = clean_text(text)
                        if clean:
                            content_areas.append(clean)
            

            if content_areas:
                combined_content = ' '.join(content_areas)
                print(f"{url}\t{combined_content}")
            else:
                print(f"No content extracted from: {url}", file=sys.stderr)
            
            time.sleep(0.5)
            
        except Exception as e:
            print(f"Error fetching {url}: {e}", file=sys.stderr)

if __name__ == "__main__":
    fetch_wikipedia_content()