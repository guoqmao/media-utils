# 流媒体工具使用说明
### 安装
1. 从`media-utils-source.zip`中解压出指定操作系统的压缩包，再次解压，
放到服务器任意目录，并将可执行文件`ffmpeg`或`ffmpeg.exe`绝对路径配置
到`application.yml`中（linux服务器请注意给`ffmpeg`文件执行权限）。
1. 项目目录执行`mvn clean package -Dmaven.skip.test=true`打包成jar包
1. 将jar包放到服务器目录，并执行`java -jar media-utils-1.0.jar`即可
开启RESTful接口服务。

### 使用
> 需要将jar运行到流媒体文件所在的服务器，通过请求RESTful接口，即可在
> 目标流媒体文件的同级目录生成转码后的文件。如需要部署在非同一服务器，
> 可通过`fastdfs`实现远程目录


### RESTful接口
* 视频转码接口（替换或删除源文件）异步

请求
```http request
POST /v1/video/_transferReplace HTTP/1.1
Content-Type: application/json

{
    "videoPath": "/home/media/34a9b0ad099f4d299fe1541c008629ba.wmv",
    "targetFormat": "mp4"
}
```
响应
```json
{
    "code": 200,
    "data": null,
    "msg": "success"
}
```

* 视频转码接口（保留源文件，生成指定名称的文件）异步

请求
```http request
POST /v1/video/_transferRename HTTP/1.1
Content-Type: application/json

{
    "videoPath": "/home/media/34a9b0ad099f4d299fe1541c008629ba.wmv",
    "newName": "sp2.mp4"
}
```

响应
```json
{
    "code": 200,
    "data": null,
    "msg": "success"
}
```