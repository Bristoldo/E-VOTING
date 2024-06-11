Vagrant.configure("2") do |config|
  config.vm.box = "hashicorp/bionic64"

  config.vm.provider "hyperv" do |hv|
    hv.memory = 2048
    hv.cpus = 2
  end
  
  config.vm.provision "shell", inline: <<-SHELL
    # Update the system
    sudo apt-get update
    
    # Install Java
    sudo apt-get install -y openjdk-17-jdk
    
    # Install Jenkins
    sudo wget -O /usr/share/keyrings/jenkins-keyring.asc https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
    echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/" | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
    sudo apt-get update
    sudo apt-get install -y jenkins
    
    # Install unzip
    sudo apt-get install -y unzip
    
    # Install Docker
    sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
    sudo apt-get update
    sudo apt-get install -y docker-ce
    
    # Add the vagrant user to the docker group
    sudo usermod -aG docker vagrant
    
    # Install Docker Compose
    sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
  SHELL

  # Configuration for Jenkins
  config.vm.network "forwarded_port", guest: 8080, host: 8080
end

# Uncomment the following section if you want to add Kubernetes setup

# Vagrant.configure("2") do |config|
#   config.vm.box = "hashicorp/bionic64"

#   config.vm.provider "hyperv" do |hv|
#     hv.memory = 2048
#     hv.cpus = 2
#   end
  
#   config.vm.provision "shell", inline: <<-SHELL
#     # Update the system
#     sudo apt-get update
    
#     # Install Kubernetes
#     sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common
#     curl -fsSL https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
#     sudo bash -c 'cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
# deb https://apt.kubernetes.io/ kubernetes-xenial main
# EOF'
#     sudo apt-get update
#     sudo apt-get install -y kubelet kubeadm kubectl

#     # Disable swap
#     sudo swapoff -a
#     sudo sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab

#     # Initialize Kubernetes
#     sudo kubeadm init --pod-network-cidr=192.168.0.0/16

#     # Set up kubeconfig for the vagrant user
#     mkdir -p $HOME/.kube
#     sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
#     sudo chown $(id -u):$(id -g) $HOME/.kube/config

#     # Apply flannel CNI
#     kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml

#     # Allow scheduling
#     # Allow scheduling on the master node
#     kubectl taint nodes --all node-role.kubernetes.io/master-
#   SHELL

#   # Configuration for Kubernetes API access
#   config.vm.network "forwarded_port", guest: 6443, host: 6443
# end