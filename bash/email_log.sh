#!/bin/bash


SCRAPERS_ARRAY=()
SUCCESS_DECREASED=()
DATE=`date +%Y-%m-%d --date="1 days ago"`
DATE_OLD=`date +%Y-%m-%d --date="2 days ago"`
SUFFIX=".log.$DATE"
FILE_EXISTS=false
TODAY=`date +%Y-%m-%d`
TH=0.6
NO_SCRAP_ATT_ALL=()
HIGH_ERRORS=()
NO_SUCCESS=()
HIGH_EMPTIES=()
REDIS=""
message=""
TIME_STEMP=`date '+%Y-%m-%d %T'`

rm $WORKSPACE/out/*.csv

file="$WORKSPACE/scrapeInStatus"
touch $file-$TODAY.csv



mkdir $WORKSPACE/out
#cp $file-$TODAY.csv $WORKSPACE/out
echo 'send email $file-$TODAY.csv'
#,ayten.khanukov@wiser.com,aviram.shahar@wiser.com
#mailx -s "Daily Scraper status - DO NOT REPLAY!!" -a "$file-$TODAY.csv" -r jenkins@wiser.com  abeniziri@quadanalytix.com, lhodadi@wiser.com, nshabi@wiser.com,  ebadiyani@quadanalytix.com, Nladak@quadanalytix.com, aandriiets@quadanalytix.com, jlayne@quadanalytix.com, ankit.singh@quadanalytix.com, sgilor@quadanalytix.com , lior.gilboa@wiser.com, akanukov@quadanalytix.com , ashahar@quadanalytix.com, dkuchel@quadanalytix.com, fali@quadanalytix.com, cng@quadanalytix.com, zlevine@quadanalytix.com, lkapp@quadanalytix.com, ynewman@quadanalytix.com, sezersky@quadanalytix.com, sKvitsynskyi@quadanalytix.com,ishapiro@quadanalytix.com, ddavis@quadanalytix.com ,wwong@quadanalytix.com<<<$'\n The attached file contains the current status for all of the active scrapers \n'
#mailx -s "Daily Scraper status - DO NOT REPLAY!!" -a "$file-$TODAY.csv" -r jenkins@wiser.com  abeniziri@quadanalytix.com<<<$'\n The attached file contains the current status for all of the active scrapers \n'
#<H1>HIGH_EMPTIES:</H1>
#<TABLE border="1" width="100%"><TH>SCRAPER and EMPTY percentage</TH>
#$(printf '<TR><TD>%s</TD></TR>' "${HIGH_EMPTIES[@]}")
#</TABLE>


#echo 'topology , count' > "$file-$TODAY.csv"
#echo $TIME_STEMP >> "$file-$TODAY.csv"

KEYS=$(echo 'keys *.P3' | redis-cli -h wisepricer-3.redis.legacy.wiser.com)
for key in $KEYS
do
a=$( echo "llen $key" | redis-cli -h wisepricer-3.redis.legacy.wiser.com)
message="$message\n $key, $a "
#echo $key, $a >> "$file-$TODAY.csv"
done
KEYS5=$(echo 'keys *.P5' | redis-cli -h wisepricer-3.redis.legacy.wiser.com)
for key in $KEYS5
do
a=$( echo "llen $key" | redis-cli -h wisepricer-3.redis.legacy.wiser.com)
message="$message\n $key, $a "
#echo $key, $a >> "$file-$TODAY.csv"
done
#echo "This is the message body" | mutt -a "/home/avi/temp_file.csv" -s "not finished cycle" -- sgilor@wiser.com
mailx -s "Not finished cycle - DO NOT REPLAY!!" -a "$file-$TODAY.csv" -r jenkins@wiser.com  sgilor@wiser.com <<<$'\n The attached file contains the current status for all of the active scrapers \n $message'

#echo "flushdb" | redis-cli -h wisepricer-3.redis.legacy.wiser.com
