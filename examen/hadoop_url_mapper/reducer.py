#!/usr/bin/env python3
import sys
import json
from collections import defaultdict

def reducer():
    """
    Reducer pentru indexul invers
    Input: word\t{URL: count}
    Output: word\t{URL_i: count_word_in_URL_i, ...}
    """
    
    # Dictionary to store aggregated results
    inverted_index = defaultdict(lambda: defaultdict(int))
    
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        
        try:
            parts = line.split('\t', 1)
            if len(parts) != 2:
                continue
                
            word, url_count_json = parts

            url_count = json.loads(url_count_json)
            
            for url, count in url_count.items():
                inverted_index[word][url] += count
                
        except Exception as e:
            print(f"Reducer error: {e}", file=sys.stderr)
            continue
    
    for word in sorted(inverted_index.keys()):
        url_counts = dict(inverted_index[word])
        url_count_str = ", ".join([f"{url}: {count}" for url, count in url_counts.items()])
        print(f"<{word}, {{{url_count_str}}}>")

if __name__ == "__main__":
    reducer()