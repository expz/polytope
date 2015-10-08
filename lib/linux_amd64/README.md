The libraries in this folder come from the following sources:

* Google.OrTools.java.Linux64.3322.tar.gz
* gmp-4.3.1-7.el6_2.2.x86_64.rpm 
* ppl-0.10.2-11.el6.x86_64.rpm
* ppl-java-0.10.2-11.el6.x86_64.rpm

After extracting the libraries from the above archives, a few links must be created:

* create symbolic link `libgmp.so` referring to `libgmp.so.3.5.0`
* create symbolic link `libppl.so` referring to `libppl.so.7.1.0`
