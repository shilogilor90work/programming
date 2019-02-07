#!/usr/bin/env bash
#
# Change all public IPs for US Azure proxies
#
# REGIONS format: <resource group name>:<VM name prefix>
#
REGIONS='l2proxy:usc usn:usn usw:usw'
echo "Refreshing Azure proxy public IPs..."
for REGION in ${REGIONS}; do
  prefix="${REGION##*:}"
  group="${REGION%%:*}"
  echo "Processing resource group: ${group}"
  for i in $(seq -w 01 16); do
    VM_NAME="${prefix}-l2pxy-${i}"
    echo "Processing system: ${VM_NAME}"

    NIC_NAME="$(az vm nic list -g $group --vm-name $VM_NAME | jq -r '.[].id' | sed -e 's/\/.*\///g')"
    OLD_IP_NAME="$(az network nic ip-config show -g $group --nic-name $NIC_NAME --name ipconfig1 | jq -r '.publicIpAddress.id')"
    NEW_IP_NAME="${VM_NAME}-$(date +%Y%m%d%H%M)"

    OLD_IP="$(az vm list-ip-addresses -g $group -n $VM_NAME | jq -r '.[].virtualMachine.network.publicIpAddresses[].ipAddress')"
    echo -e "\tCurrent IP: ${OLD_IP}"
    #echo -e "\tStop the instance"
    #az vm deallocate -g $group --name $VM_NAME
    #az vm stop -g $group --name $VM_NAME
    echo -e "\tCreating new IP"
    # What to do with output?
    az network public-ip create -g $group --name $NEW_IP_NAME --allocation-method Static
    # | jq -r .publicIp.ipAddress
    CREATED_IP="$(az network public-ip show -g $group --name $NEW_IP_NAME | jq -r .ipAddress)"
    echo -e "\tCreated new IP: ${CREATED_IP}"
    echo -e "\tAttaching it to the NIC"
    # What to do with output?
    az network nic ip-config update -g $group --name ipconfig1 --nic-name $NIC_NAME --public-ip-address $NEW_IP_NAME
    echo -e "\tDeleting old IP"
    az network public-ip delete -g $group --id $OLD_IP_NAME
    NEW_IP="$(az vm list-ip-addresses -g $group -n $VM_NAME | jq -r '.[].virtualMachine.network.publicIpAddresses[].ipAddress')"
    echo -e "\tIP Changed: ${OLD_IP} -> ${NEW_IP}"
    #echo -e "\tStarting instance"
    #az vm start -g $group --name $VM_NAME
  done
done
