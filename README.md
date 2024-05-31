# morbus
<b>질병을 파악하고 해결책을 제시해주는 서비스</b>
<p><br></p>

# 설치 방법

1. java 설치
```sh
~$ sudo apt update
~$ java --version
~$ sudo apt install openjdk-21-jdk openjdk-21-jre -y
~$ vi ~/.bashrc
export JAVA_HOME="/usr/lib/jvm/java-21-openjdk-amd64"
PATH=$PATH:/home/[username]/.local/bin         맨 아래 줄에 코드 2줄 추가
~$ source ~/.bashrc
```

2.h2 DB 설치및 테이블 설정
(Mac or Linux) https://www.h2database.com (모든 os 전용 다운)
```sh
~$ cd bin
~$ chmod 755 h2.sh
~$./h2.sh
```
(Windows) http://www.h2database.com (windows or 모든 os 전용 다운)
```sh
~$ cd bin
~$./h2.bat
```
스프링 부트 2.x를 사용하면 1.4.200 버전을 다운로드 받으면 된다.
스프링 부트 3.x를 사용하면 2.1.214 버전 이상 사용해야 한다.

3.gradle 설치
```sh
$ sudo add-apt-repository ppa:cwchien/gradle
$ sudo apt update
$ sudo apt install gradle
$ gradle -version
```

# 의존성
<h4>OS</h4>
windows Mac Linux

<h4>Library</h4>
Spring Boot 3.2.5 (Spring Web thymeleaf) / java 21.0.2 / Node.js

<h4>DB</h4>
h2

# 실행 방법
최초 1회
```sh
1. ~$ git clone https://github.com/jeongseonwoong/morbus.git
2.h2.bat or h2.sh 실행
3.JDBC URL:jdbc:h2:~/morbus_user 연결
4.아래 코드 실행 (테이블 설정)
create table member
( id bigint generated by default as identity,
 name varchar(255),
 primary key (id)
);
create table symptom_record (
id int auto_increment primary key,
symptom text not null,
timestamp datetime not null,
record_key long
); 
5.JDBC URL: jdbc:h2:tcp://localhost/~/morbus_user로 변경 후 접속
~$cd morbus/Server
~$ sudo chmod 755 gradlew
~$ gradlew build
~$ gradlew bootRun
```
최초 1회 이후
```sh
1.h2.bat or h2.sh 실행
2.jdbc:h2:tcp://localhost/~/test로 접속
~$ cd morbus/Server
~$ ./gradlew build	
~$ ./gradlew bootRun
안되면 IDEA에서 실행!

```

# 라이선스
MIT License

Copyright (c) 2024 정선웅

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
<p><br></p>

# 멤버
<ul>
  <li>정선웅</li>
  <a href="https://github.com/jeongseonwoong">GitHub 주소</a>
  <br>01027993550
  <li>조진우</li>
  <a href="https://github.com/JJW1223">GitHub 주소</a>
  <p> @JJW1223 <br>01087713302 </p>
  <li>양희창</li>
  <a href="https://github.com/YangHeeChang">GitHub 주소</a>
  <p>@YangHeeChang <br>01024733055 </p>
</ul>
