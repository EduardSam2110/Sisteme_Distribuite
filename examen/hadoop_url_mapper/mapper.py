#!/usr/bin/env python3
import sys
import re
import json

def mapper():
    """
    Mapper pentru indexul invers
    Input: URL\ttext_content
    Output: word\t{URL: 1}
    """
    
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        
        try:
            # Parse input: URL\ttext_content
            parts = line.split('\t', 1)
            if len(parts) != 2:
                continue
                
            url, content = parts
            
            # Extract words (only alphabetic characters, minimum 3 letters)
            words = re.findall(r'\b[a-zA-Z]{3,}\b', content.lower())
            
            # Count word occurrences for this URL
            word_counts = {}
            for word in words:
                word_counts[word] = word_counts.get(word, 0) + 1
            
            # Emit each word with its count for this URL
            for word, count in word_counts.items():
                # Output format: word\t{URL: count}
                url_count = {url: count}
                print(f"{word}\t{json.dumps(url_count)}")
                
        except Exception as e:
            # Log errors to stderr (won't interfere with output)
            print(f"Mapper error: {e}", file=sys.stderr)
            continue

if __name__ == "__main__":
    mapper()