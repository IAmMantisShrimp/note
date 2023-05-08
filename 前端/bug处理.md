# 1.node版本

## 1.node版本导致下载依赖问题

```txt
22 verbose argv "F:\\Environment\\nodejs\\node.exe" "F:\\Environment\\nodejs\\node_modules\\npm\\bin\\npm-cli.js" "run" "dev"
23 verbose node v14.17.6
24 verbose npm  v6.14.15
25 error code ELIFECYCLE
26 error errno 1
27 error zte_jxfmw@0.6.9 dev: `vue-cli-service build --watch  --target lib src/module.js --dest ./example/public/dist`
27 error Exit status 1
28 error Failed at the zte_jxfmw@0.6.9 dev script.
28 error This is probably not a problem with npm. There is likely additional logging output above.
29 verbose exit [ 1, true ]
```

![img](https://upload-images.jianshu.io/upload_images/14707169-122d2b7dc4c231ef.png?imageMogr2/auto-orient/strip|imageView2/2/w/892/format/webp)

先删除node_modules,lock.json 然后清除缓存,最后创新npm install

即,以此执行以下代码:

```txt
rm -rf node_modules
rm package-lock.json
npm cache clear --force
npm install
```



