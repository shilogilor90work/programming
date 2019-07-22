import sys
import csv
import json

def fix_nulls(s):
    for line in s:
        yield line.replace('\0', '')

csv.field_size_limit(sys.maxsize)
# Open the CSV
f = fix_nulls(open( '../data/' + sys.argv[1] + '.csv', 'r' ,encoding='utf-8-sig'))
csv_reader = csv.reader(f)
csv_headings = next(csv_reader)
# Change each fieldname to the appropriate field name. I know, so difficult.
reader = csv.DictReader( f, fieldnames = ( csv_headings ))

# Parse the CSV into JSON
# out = json.dumps( [ row for row in reader ] )
print ("JSON parsed!")
# Save the JSON
#f = open( '/path/to/parsed.json', 'w')
#f.write(out)
final = {
    "system": "Name of system",
    "api_url": "url for api of system",
    "items": [r for r in reader],
}
#final into json
final = json.dumps(final)
f = open( sys.argv[1] +'.json', 'w')
f.write(final)
