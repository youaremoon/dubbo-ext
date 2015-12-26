# dubbo-ext
dubbo extension
在原生dubbo(https://github.com/alibaba/dubbo)的基础上做功能扩展，目前主要是在2.5.4-SNAPSHOT的基础上扩展，如果用于其他版本需要自测。

1.dubbo-serialize-protobuf 
    dubbo的默认序列化方式hessian是不支持protobuf的，要支持protobuf需要使用java默认的序列化。
protobuf虽然对java默认序列化有支持，但如果应用中还是用了非protobuf的实体，采用java序列化效率就低了。
dubbo-serialize-protobuf在hessian的基础上进行扩展，对于普通实体依然采用hessian，对于protobuf则直接利用protobuf框架
的能力。让你的应用既可以支持protobuf实体，也可以支持普通的java对象，且保持高效率。

2.dubbu-remoting-newnetty
    dubbo使用netty3作为其默认的网络传输框架。而netty目前已经发展到netty4(稳定版),netty4中引入了内存池，能够大大降低gc的频率。
由于netty3与netty4无法兼容，这里将dubbo中的netty3升级到了netty4。
