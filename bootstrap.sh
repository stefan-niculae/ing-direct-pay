#!/usr/bin/env bash

cp /vagrant/provisioning/id_rsa ~vagrant/.ssh/
cp /vagrant/provisioning/known_hosts ~vagrant/.ssh/

chown -R vagrant:vagrant ~vagrant/.ssh
chmod -R go-rwx ~vagrant/.ssh

apt-get update
apt-get install -y git

