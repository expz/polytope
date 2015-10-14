The libraries in this folder come from the following sources:

* Google.OrTools.java.Linux64.3322.tar.gz
* gmp-6.0.0-11.el7.x86_64.rpm
  ftp://fr2.rpmfind.net/linux/centos/7.1.1503/os/x86_64/Packages/gmp-6.0.0-11.el7.x86_64.rpm
* ppl-1.1-7.fc22.x86_64.rpm
  ftp://fr2.rpmfind.net/linux/fedora/linux/releases/22/Everything/x86_64/os/Packages/p/ppl-1.1-7.fc22.x86_64.rpm
* ppl-java-1.1-7.fc22.x86_64.rpm
  ftp://fr2.rpmfind.net/linux/fedora/linux/releases/22/Everything/x86_64/os/Packages/p/ppl-java-1.1-7.fc22.x86_64.rpm

After extracting the libraries from the above archives, a few links must be created:

* create symbolic link `libgmp.so` referring to `libgmp.so.3.5.0`
* create symbolic link `libppl.so` referring to `libppl.so.7.1.0`
