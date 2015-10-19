#!/bin/sh
#
# Download required native libraries
#
if ! [ -e com.google.ortools.jar ]; then
  curl -s 'https://github.com/google/or-tools/releases/download/v2015-09/Google.OrTools.java.Linux64.3322.tar.gz' \
    | sed -n "s/.*href=\"\([^\"]*\)\".*$/'\1'/p" \
    | sed -e 's/&amp;/\&/g' \
    | xargs curl -s \
    | tar -xz
  mv or-tools.Linux64/lib/* .
  chmod 755 *.so
  chmod 644 *.jar
  rm -r or-tools.Linux64
fi

# https://github.com/google/or-tools/releases/download/v2015-09/Google.OrTools.java.Linux32.3322.tar.gz
# https://github.com/google/or-tools/releases/download/v2015-09/Google.OrTools.java.Linux64.3322.tar.gz
# https://github.com/google/or-tools/releases/download/v2015-09/Google.OrTools.java.MacOsX64.3322.tar.gz 
# https://github.com/google/or-tools/releases/download/v2015-09/Google.OrTools.java.VisualStudio2013-32b.3322.zip
# https://github.com/google/or-tools/releases/download/v2015-09/Google.OrTools.java.VisualStudio2013-32b.3322.zip

if ! [ -e ppl_java.jar ]; then

  # 32-bit linux libs
  # http://dl.fedoraproject.org/pub/fedora/linux/releases/22/Everything/i386/os/Packages/p/ppl-1.1-7.fc22.i686.rpm
  # http://dl.fedoraproject.org/pub/fedora/linux/releases/22/Everything/i386/os/Packages/p/ppl-java-1.1-7.fc22.i686.rpm
  # http://dl.fedoraproject.org/pub/fedora/linux/releases/22/Everything/i386/os/Packages/g/gmp-6.0.0-9.fc22.i686.rpm

  # read from standard in not implemeneted in 7z
  curl 'http://dl.fedoraproject.org/pub/fedora/linux/releases/22/Everything/x86_64/os/Packages/g/gmp-6.0.0-9.fc22.x86_64.rpm' -o gmp.rpm \
    && 7z e gmp.rpm \
    && mkdir -p temp \
    && 7z e gmp.cpio -otemp -y \
    && curl 'http://dl.fedoraproject.org/pub/fedora/linux/releases/22/Everything/x86_64/os/Packages/p/ppl-1.1-7.fc22.x86_64.rpm' -o ppl.rpm \
    && 7z e ppl.rpm \
    && mkdir -p temp \
    && 7z e ppl.cpio -otemp -y \
    && curl 'http://dl.fedoraproject.org/pub/fedora/linux/releases/22/Everything/x86_64/os/Packages/p/ppl-java-1.1-7.fc22.x86_64.rpm' -o ppl-java.rpm \
    && 7z e ppl-java.rpm \
    && mkdir -p temp \
    && 7z e ppl-java.cpio -otemp -y

  mv temp/*.so.* temp/*.so temp/*.jar . \
    && ln -s libgmp.so.10.2.0 libgmp.so \
    && ln -s libgmpxx.so.4.4.0 libgmpxx.so \
    && ln -s libppl_c.so.4.0.0 libppl_c.so \
    && ln -s libppl.so.13.0.0 libppl.so \
    && chmod 755 *.so *.so.* \
    && chmod 644 *.jar \
    && rm -r ./temp *.cpio *.rpm

fi

#libgmp.so
#libgmp.so.10
#libgmp.so.10.2.0
#libgmpxx.so
#libgmpxx.so.4
#libgmpxx.so.4.4.0
#libjniortools.so
#libmp.so
#libmp.so.3
#libmp.so.3.1.14
#libppl_c.so
#libppl_c.so.4
#libppl_c.so.4.0.0
#libppl_java.so
#libppl.so
#libppl.so.13
#libppl.so.13.0.0
#LICENSE-gmp.txt
#LICENSE-ortools.txt
#LICENSE-ppl.txt
#ppl_java.jar
#README.md
