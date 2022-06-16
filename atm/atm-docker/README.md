# docker部署环境的文件夹

对应的文件夹是

**一些内容被gitignore掉了，请自行在文件夹中配置相关环境**

说的没错，就是mysql，对应的数据卷挂载为

```yaml
volumes:
  - "$PWD/mysql/data:/var/lib/mysql"
  - "$PWD/mysql/conf:/etc/mysql"
```