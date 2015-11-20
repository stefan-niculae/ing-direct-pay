#!/usr/bin/env bash

cp /vagrant/provisioning/id_rsa ~vagrant/.ssh/
cp /vagrant/provisioning/known_hosts ~vagrant/.ssh/

chown -R vagrant:vagrant ~vagrant/.ssh
chmod -R go-rwx ~vagrant/.ssh

apt-get update
apt-get install -y git

apt-get install -y openjdk-7-jdk
apt-get install -y leiningen
apt-get install -y gradle
apt-get install -y maven

sudo wget -qO- https://raw.githubusercontent.com/creationix/nvm/v0.29.0/install.sh | HOME=/home/vagrant sudo -u vagrant bash

