#!/bin/bash
echo "--=== Incoming Parameters (This script should be reusable) ===--"
echo "[P1] Version Number is :$1 "
echo "[P2] Target Server is :$2 "
echo "[P3] Target Folder is :$3 "
echo "---------------------------------------"
echo "Dependancies to install with sudo apt are:"
echo "python"
echo "pip"
echo "python-sphinx"
echo "---------------------------------------"

echo "--=== Run local Build ===--"
pip install -r requirements.txt
./build.sh
echo "-----------------------------------------"
echo "Version $1" > .//version.html
echo "--------------------------------------"

echo "--=== Transfer files to remote Server ===--"
echo "rsync -avzhe ssh  --rsync-path='rsync' ./build jenkins@$2:$3"
rsync -avzhe ssh  --rsync-path="rsync" ./build/html/ jenkins@$2:$3
echo "---------------------------------------"

echo "----====== Start up Service for cron to keep system live ======----"
echo "---------------------------------------"

echo "----====== Verify Deployments-List from Remote ======----"
ssh -p 22 $2 "ls -al $3"
echo "---------------------------------------------------------"

echo "--=== Version Deployed is [$1] The following output from version.info ===--"
ssh -p 22 $2 "cat $3version.html"
ssh -p 22 $2 "forever list"

echo "------------The-End-------------------------------------------------------"
