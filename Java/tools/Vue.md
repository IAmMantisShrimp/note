## Vue学习

## 1.基础

### 1.1 template标签

问题描述:有诺干个元素同生共死

解决:

```html
<!--方式一:代码复用,缺点也明显,代码冗余-->
<div id="box">
  <div v-if="isCreated">aaaa</div>
  <div v-if="isCreated">bbbb</div>
  <div v-if="isCreated">cccc</div>
</div>
  <script>
    var app=new Vue({
      el:"#box",
      data:{
        isCreated:true,
      }
    })
  </script>
```

```html
<!--方式二:加一层,缺点:破坏布局结构-->
<div id="box">
  <div v-if="isCreated">
    <div>aaaa</div>
    <div>bbbb</div>
    <div>cccc</div>
  </div>
</div>
```

![image-20220419145007694](G:\Document\mdNote\Typora\image-20220419145007694.png)



```html
<!--方式三:使用template标签(vue自带),不影响布局-->
<div id="box">
  <template v-if="isCreated">
    <div>aaaa</div>
    <div>bbbb</div>
    <div>cccc</div>
  </template>
</div>
```

![image-20220419145217540](G:\Document\mdNote\Typora\image-20220419145217540.png)

![image-20220419145303961](G:\Document\mdNote\Typora\image-20220419145303961.png)





## 2.@input事件

问题描述: 在输入框输入信息,再根据信息筛选列表.

| ![源数据](G:\Document\mdNote\Typora\image-20220419212140707.png)![image-20220419212154363](G:\Document\mdNote\Typora\image-20220419212154363.png) |      |
| :----------------------------------------------------------: | ---- |
|                                                              |      |

```javascript
<script>
  let app=new Vue({
    el:"#box",
    data:{
      listArr:["aaa","bbb","ccc","ddd"],
      listTemp:["aaa","bbb","ccc","ddd"],
      myText:"",

    },
    methods:{
      handleInput(){
        console.log("input " + this.myText)
        //使用数组的filter方法,将符合的元素过滤出来
        //注意:这里包含方法是includes
        var newList=this.listTemp.filter(item=> item.includes(this.myText))
        this.listArr=newList
        // console.log(newList)
      }
    }
  })

</script>
```

优化如下:↓

## 3.函数表达式

问题描述: 在输入框输入信息,再根据信息筛选列表.

```
<div id="box">
  <!--input:内容改变时触发-->
  <input type="text"  v-model="myText"/>
  <ul>
    <li v-for="item in handleList()" :key="item">
      {{item}}
    </li>
  </ul>
</div>
<script>
  let app=new Vue({
    el:"#box",
    data:{
      listArr:["aaa","bbb","ccc","ddd"],
      myText:"",

    },
    methods:{
      //随着myText改变,handleList引用了myText所以也会被拦截并再次执行
      handleList(){
        return this.listArr.filter(item=> item.includes(this.myText))
      }
    }
  })

</script>
```





## 4.事件冒泡原则

可以用.stop或self屏蔽冒泡

## 5. evt 获取事件对象

```
<div id="box">
  {{count}}
  <!--可以传参-->
  <button @click="handleAdd1()">add-1</button>
  <!--不传参,得到一个默认对象,事件对象evt-->
  <button @click="handleAdd2">add-2</button>
  <!--可以传参,也能获取事件对象evt,$event必须为第一个参数-->
  <button @click="handleAdd2($event)">add-3</button>
  <ul @click="handleUl()">
    <li @click="handleLi()">
      hello
    </li>
  </ul>
</div>
<script>
  let app=new Vue({
    el:"#box",
    data:{
      count:1,
    },
    methods:{
      handleAdd1(){
        this.count++
      },
      //无小括号,会传个evt值,可以获取当前标签名
      handleAdd2(evt){
        this.count++
        console.log(evt.target)//<button>add-2</button>
      },
      handleAdd3(evt){
        console.log(evt.target)//<button>add-2</button>
      }
    }
  })

</script>
```

## 6.复选框记录值

```
<div>
  <h1>兴趣爱好</h1>
  <!--每个复选框都设一个变量太麻烦,
      因此这里用一个数组和value来存储已经勾选的复选框,
  -->
  <input type="checkbox" v-model="checkBoxList" value="vue"/> vue
  <input type="checkbox" v-model="checkBoxList" value="react"> react
  <input type="checkbox" v-model="checkBoxList" value="java"> java
  {{checkBoxList}}
</div>
```

![image-20220420210600439](G:\Document\mdNote\Typora\image-20220420210600439.png)

## 7. lazy修饰符

有些双向绑定情形不需要每输入一个字符就响应一次,而是希望失去焦点或触发其它事件时响应.可以使用v-model.lazy来绑定标签

## 8. number修饰成数字类型

v-model.number

```
{{myText}}
<p>实时改变</p>
<input type="text" v-model="myText"><br>
<p>失去焦点改变</p>
<input type="text" v-model.lazy="myText"><br>
<p>获取数字部分</p>
<input type="text" v-model.number="myText"><br>
 <p>去首位空格</p>
<input type="text" v-model.trim="myText"><br>
```

![image-20220420221525930](G:\Document\mdNote\Typora\image-20220420221525930.png)

## 9.动态绑定组件

当有多个组件选择性显示时,可以用

<component is=""></component>标签.

如:

```
自定义组件child
<child></child>
自定义组件parent
<parent></parent>
此时,绑定的是child组件
<component is="child"></component>

也可以用变量动态绑定,改变which值就可以选择使用哪个组件
<component :is="which"></component>
```

## 10.前端请求外部url跨域问题

### 1.前端解决

先安装axios,   npm install --save axios



F:\JavaWeb\Vue\SutdyModule\m1_test\src\components\AxiosGetUrl.vue

如果直接请求外部网址:

```
axios.get("https://ditu.amap.com/service/regeo?longitude=121.04925573429551&latitude=31.315590522490712").then(res=>{
  console.log(res.data)
})
```

会出现跨域问题:

![image-20220425160306461](G:\Document\mdNote\Typora\image-20220425160306461.png)

解决:代理转发

![image-20220425160747166](G:\Document\mdNote\Typora\image-20220425160747166.png)

#### 1.拆分url

将url拆分成域名和请求路径:

域名:https://ditu.amap.com

请求路径:/service/regeo?longitude=121.04925573429551&latitude=31.315590522490712

#### 2.更改url为路径:

```
//axios请求外部url需要跨域
//  解决:在vue.confit.js中配置反向代理,让后端服务器发请求
axios.get("/service/regeo?longitude=121.04925573429551&latitude=31.315590522490712").then(res=>{
  console.log(res.data)
})
```

#### 3.配置代理

注意要配置在vue.config.js中,如果没有这个文件就新建,配置好后也不用导入到其他文件中

```
//  配置反向代理
devServer:{
  proxy:{
    //凡是/service开头的请求,代理到target域名地址下
    '/service':{
      target:"https://ditu.amap.com",
      changeOrigin:true
    }
  }
  }
```

![image-20220425161103842](G:\Document\mdNote\Typora\image-20220425161103842.png)



```javascript
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  //  配置反向代理
  devServer:{
    proxy:{
      //方式一:  凡是/service开头的请求,代理到target域名地址下
      // '/service':{
      //   target:"https://ditu.amap.com",
      //   changeOrigin:true
      // }

      //方式二:可以将开头部分去除掉
      "/tianqi":{
        target:"https://ditu.amap.com",
        changeOrigin:true,
        pathRewrite:{
          //要替换数据(支持正则表达式) : 替换为数据
          "^/tianqi":""
        //  ^ 为以什么开头,替换成空字符串
        }
      }
    }
  }
})

```



### 2.后端解决

加一个配置类即可:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 前端跨域问题处理
 * @author: HuaRunSheng
 * @date: 2022/5/16 11:31
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许访问的路径
                .addMapping("/**")
                //配置请求来源
                .allowedOrigins("http://localhost:8080")
                //允许跨域访问的方法
                .allowedMethods("GET","POST","DELETE","PUT","OPTION")
                //是否允许请求头
                .allowCredentials(true)
                //最大响应时间
                .maxAge(3600);
    }
}

```



### 4.重启项目

## 11.路由

https://blog.csdn.net/begefefsef/article/details/123304297

### 1.新建两个vue组件

### 2.新建一个router.js

代码如下:

```vue
// history模式
import {
    createRouter,
    createWebHashHistory,
} from 'vue-router'

import Home from '../pages/Home.vue'
import About from '../pages/About.vue'

const routes = [
// 路由的默认路径
    {
        path:'/',
        redirect:"/home"
    },
    {
        path: '/home',
        component: Home
    },
    {
        path: '/about',
        component: About
    },
]

// 创建路由对象
const router = createRouter({
    history: createWebHashHistory(),
    routes
})
export default router;

```

### 3.将router.js导入到main.js中

```
import { createApp } from 'vue'
import App from './App.vue'
import router from './router.js'

createApp(App).use(router).mount('#app')
```

### 4.在App.vue中加入路由组件

```vue
<template>
  <div>
    <router-link to="/home">home</router-link>
    <router-link to="/about">about</router-link>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
};
</script>

<style>
</style>

```

router-link
上面有提到过router-link，下面我们来简单介绍一下他的使用
router-link事实上有很多属性可以配置：

 to属性：是一个字符串，或者是一个对象
		replace属性：设置 replace 属性的话，当点击时，会调用 router.replace()，而不是 					router.push()；
		active-class属性：设置激活a元素后应用的class，默认是router-link-active
		exact-active-class属性：链接精准激活时，应用于渲染的 <a> 的 class，默认是router-link-					exact-active；

路由懒加载
如果我们能把不同路由对应的组件分割成不同的代码块，然后当路由被访问的时候才加载对应组件，这样就会
更加高效；

这里可以使用webpack的分包知识，而Vue Router默认就支持动态来导入组件

这是因为component可以传入一个组件，也可以接收一个函数，该函数 需要放回一个Promise；

而import函数就是返回一个Promise；

```javascript
const routes = [{undefined
path: ‘/’,
redirect: “/home”
},
{undefined
path: ‘/home’,
component: () => import(’…/pages/Home.vue’)
},
{undefined
path: ‘/about’,
component: () => import(’…/pages/About.vue’)
},
]
```

### 4.动态路由

:名字

```
{
  //动态路由,必须是/films/detail/***才能跳转
  path: '/films/detail/:myId',
  component: () => import('./router/films/FilmDetail.vue')
},
```

### 5.激活样式

首先在route.js中创建自定义样式:

```
// 创建路由对象
const router = createRouter({
  // mode: 'history',
  history: createWebHashHistory(),
  routes,
  //自定义激活样式
  linkActiveClass: 'hrsActive'
})
```

在template中使用自定义的样式

```
<router-link to="/films" custom v-slot="{navigate,isActive}">
  <li @click="navigate" :class="isActive?'hrsActive':''">
    影院
  </li>
</router-link>

<router-link to="/center" custom v-slot="{navigate,isActive}">
  <li @click="navigate" :class="isActive?'hrsActive':''">
    用户中心
  </li>
</router-link>
```

最后在style中设置激活样式

```
<style scoped>
/*设置当前选中的背景色*/
.router-link-active,
.hrsActive {
  color: red;
  /*font-weight: 800;*/
  /*font-style: italic;*/
  /*font-size: 80px;*/
  /*text-decoration: underline;*/
  background-color: green;
}

</style>
```

![image-20220428111754589](G:\Document\mdNote\Typora\image-20220428111754589.png)

## 12.路径跳转

### 1.可以使用路由

```
<router-link to="/films/detail">
  {{data}}
</router-link>
```

### 2.事件触发

```
methods:{
      //编程式导航
        //1.旧写法,需要加#号,高亮要自己设置
      // location.href="#/films/detail"
        //2.新写法
      // 跳转到登录页面
      // this.$router.replace("/login");
      this.$router.push("/films/detail")
      //3.通过命名路径跳转
      this.$router.push({
        // 路由别名
        name:"fileDetail",
        params:{
          id:id,
        }
      })
}
```

## 13.获取当前页面的url

### 1.获取完整路径

location.href

http://localhost:8080/#/films/detail/11111

### 2.获取局部路径(当前路由)

```
this.$route
```

![image-20220426191530665](G:\Document\mdNote\Typora\image-20220426191530665.png)

如:url=http://localhost:8080/#/films/detail/11111

this.$route.params	为 11111

this.$route.path 为 /films/detail/11111



## 14,url拦截

### 1.全局拦截

```
router.beforeEach((to,from,next)=>{
  // to:请求路径信息
  console.log('to: ',to)
  // from,原来的路径信息
  console.log('from: ',from);
  // next:放行
  if (true){
    next()
  }else {
    //
    // 也可以指向某一个url
    next('/login')
  }
})
```

![image-20220426232415305](G:\Document\mdNote\Typora\image-20220426232415305.png)

### 2.使用指定义字段作为条件

在路径中加入meta对象,并自定义属性

```
{
  path: '/center',
  component: center,// 自定义属性
  meta:{
    isHrsRequired:true,
  }

},
```

使用:

先在login中存储一个token:

```vue
methods:{
  LoginIn(){
    setTimeout(()=>{
      // 存储session
      localStorage.setItem("token","后端返回的数据")
      // 返回到上一个页面
      // this.$router.back()
      this.$router.push("/center")
    })
  }
}
```

在router.js中配置拦截

```
router.beforeEach((to,from,next)=>{
  // to:请求路径信息
  console.log('to: ',to)
  // from,原来的路径信息
  console.log('from: ',from);
  // next:放行

  if(to.meta.isHrsRequired){
    // 如果本地有isHrsRequired属性
    // if (to.fullPath === "/films/now"){
    //   next()
    // }else {
    //   //
    //   // 也可以指向某一个url
    //   next('/login')
    // }
    next()
    if (localStorage.getItem("token")){
      // 判断本地是否存储了token(相当于session)
      next()
    }else{
      next("/login")
    }
  }
})
```

![image-20220426233821076](G:\Document\mdNote\Typora\image-20220426233821076.png)



### 3.局部拦截

和全局拦截一样,但是拦截方法卸载路径中

```
const routes = [
// 路由的默认路径
  {
    // name:给路由起别名
    name: "default",
    path:'/',
    redirect:"/films",
    beforeEnter:((to,from,next)=>{
      
    })
  }]

```

只有在此路径下,这个拦截配置才生效

![image-20220427145916856](G:\Document\mdNote\Typora\image-20220427145916856.png)



### 4.某个页面拦截

拦截配置写在某个页面上,

```
beforeRouteEnter(to,from,next) {... ...}
```

![image-20220427150249850](G:\Document\mdNote\Typora\image-20220427150249850.png)

## 15.返回到上一个页面

### 1.使用next()

原来页面:http://localhost:8080/#/films/now

跳转页面:http://localhost:8080/#/login?redirect=/films/now

将上一个页面的路径(/films/now)通过参数传入到跳转的url中

使用:

在跳转时,在next()加入query,

```
router.beforeEach((to,from,next)=>{
  // to:请求路径信息
  console.log('to: ',to)
  // from,原来的路径信息
  console.log('from: ',from)
  // next:放行
  if(to.meta.isHrsRequired){

    if (localStorage.getItem("token")){
      // 判断本地是否存储了token(相当于session)
      next()
    }else{
      console.log("未登录")
      // next("/login")
      next({
        path:"/login",
        // 将当前的url传如入到query中,处理完可以再转回来
        query:{redirect: to.fullPath}
      })
    }
  }else{
    next()
  }
})
```

在跳转到的页面中,将query打印出来

```
console.log(this.$route.query)
```

![image-20220427144654651](G:\Document\mdNote\Typora\image-20220427144654651.png)

可以使用this.$route.query.redirect取出上一个页面路径

```
// 跳转到上一个页面
this.$router.push(this.$route.query.redirect)
```



### 2.使用this.$router.back()



## 16.fontsize计算







```

```

```javascript
<script>
  //fontsize 计算
  document.documentElement.style.fontSize=document.documentElement.clientWidth/750*100+'px'
</script>
```

## 17.操作数组

```
    var FilmsList=[]
    axios.get("/mmaizuo.json").then(res=>{
      // console.log(res.data.data.films)
      FilmsList=res.data.data.films

      //将字符串转数字
      var filmId=parseInt(this.$route.params.id)
      //打印数组
      console.log("this.FilmsList: ",FilmsList)
      //获取对象类型
      console.log("typeof",typeof (this.$route.params))
      //打印字符长度
      console.log("arr length:",FilmsList.length)
      // 遍历数组
      for (var index=0; index < FilmsList.length; index++){
        console.log("filmId: ",FilmsList[index].filmId)
        if (FilmsList[index].filmId == filmId){
          console.log("找到了,哈哈哈 ")
        }
      }
    })
```

## 18.动态设置字符大小

导入包

npm install postcss-pxtorem --save-dev

在package.json中加入这段

```
"postcss": {
  "plugins": {
    "autoprefixer": {
      "postcss-pxtorem": {
        "rootValue": 37.5,
        "propList": ["*"]
      }
    }
  }
}
```

### 1.新建一个rem.js文件

```
function setRem() {
  let htmlWidth = document.documentElement.clientWidth || document.body.clientWidth

  let htmlDom = document.querySelector("html")
  // 最大为18px
  let fontSize=htmlWidth/35 > 18 ? 18:htmlWidth/35
  htmlDom.style.fontSize = fontSize + "px"
}
setRem()
window.onresize = function () {
  setRem()
}
```

### 2.导入到main.js中

```
import { createApp } from 'vue'
import App from './App.vue'
import router from './router.js'
import "./rem.js"
createApp(App).use(router).mount('#app')

```

![image-20220428114616388](G:\Document\mdNote\Typora\image-20220428114616388.png)

## 19.给标签绑定一个样式

### 1.首先新建一个style

```
 .film .film-detail .voerflow {
  overflow: hidden;
}
```

### 2.将style用:class绑定到标签中

初始化值:

```
data(){
  return{
    FilmId:"",
    FilmObj:{},
    isHidden:true,

  }
```

绑定到标签中

```
<div class="film-synopsis grey-text hidde" style="height: 39px;"
:class="isHidden?'voerflow':''">
  {{FilmObj.synopsis}}
</div>
```

注意:class是绑定,并未在class中,只有条件成立才加入class,且:class绑定的样式不能与class中的同名

如此时class="film-synopsis grey-text hidde",则:class="hidden会变得无效"

### 3.触发条件

```
<div class="toggle"><img
    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAICAYAAADwdn+XAAAAAXNSR0IArs4c6QAAAQlJREFUKBWNkT1Lw1AUht8TIlJExKlj/4WQFNIacWyHCoLQ1cXdj59g7e7i6CA4OAhOpbdNIKn2X3Ts1NGhocdzbgmk1NJmuCHnvM+Tc+8lEyVfDBzDxdVZtTrBDk8/SSrI8EbAzBH4EAwPc/qJ4/RkG28zklVGWefoYL8BQg/gcsYYmmjU2iQx8feFZjSrjLIyBTBgdjlKn8V4TUQszfsw8LtFkYnTW/lrh5lJoBeq+TenRJkV5EEzHN2B+LEY0t6KnOkhrHtPObMi0KKOCV68iqS03JoacC6T/QLUDmveRw7re02gRT2o+QKfdq9aAE33HDSDwB/bz8Lyr0D7y6uid5t1+XLTFf8Bk+NpPwQfd0oAAAAASUVORK5CYII="
    alt="" class="" @click="HiddenHandle"></div>
</div>
```

@click="HiddenHandle"

```
methods:{
  HiddenHandle(){
    console.log("HiddenHandle:",this.isHidden)
    this.isHidden=!this.isHidden
  }
},
```

![image-20220428180708056](G:\Document\mdNote\Typora\image-20220428180708056.png)

![image-20220428180810990](G:\Document\mdNote\Typora\image-20220428180810990.png)

## 20.Element组件使用

官网:https://element.eleme.cn/

简单使用:https://blog.csdn.net/weixin_53068161/article/details/116543302

### 1.安装

Vue3:https://element-plus.org/zh-CN/guide/installation.html#%E4%BD%BF%E7%94%A8%E5%8C%85%E7%AE%A1%E7%90%86%E5%99%A8

npm install element-plus



Vue2:https://element.eleme.cn/#/zh-CN/component/installation

npm i element-ui -S

```JavaScript
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
```



## 21.创建Vue2项目

### 1.在终端中输入:vue create 名称

注意名称不可用大写英文,如:

vue create m2_vue2_test

![image-20220429123059297](G:\Document\mdNote\Typora\image-20220429123059297.png)

### 2.根据自己的需求,选择vue2

![image-20220429123129327](G:\Document\mdNote\Typora\image-20220429123129327.png)

### 3. 启动项目

先进入创建项目的目录: cd xxx

然后执行如下命令，启动vue项目

```bash
npm run serve
```

### 4.导入为module

![image-20220429123854527](G:\Document\mdNote\Typora\image-20220429123854527.png)

找到需要导入的模块:

![image-20220429123927926](G:\Document\mdNote\Typora\image-20220429123927926.png)

一直next即可

![image-20220429123959613](G:\Document\mdNote\Typora\image-20220429123959613.png)

## 22.ts TypeScript环境支持

```
<script lang="ts" setup>
```

 @vue/cli-plugin-typescript

需要安装ts插件

## 23.封装数组

```
const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}
const tableData = ref(Array.from({ length: 20 }).fill(item))
```

构造一个长度为20的对象数组



## 24.vue3 使用element-puls中icon ，不使用ts

https://blog.csdn.net/m0_59962049/article/details/123386506

使用前- 安装element-plus 的icon

### 选择一个你喜欢的包管理器

#### NPM
$ npm install @element-plus/icons-vue
#### Yarn
$ yarn add @element-plus/icons-vue
#### pnpm
$ pnpm install @element-plus/icons-vue

### 1.main.js

```
import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import elementIcon from "./plugins/svgicon";//实现element-puls icon的办法
import installElementPlus from "./plugins/element";//实现element-puls icon的办法


createApp(App).use(ElementPlus)
  .use(installElementPlus)
  .use(elementIcon)
  .mount('#app')
```

## 25.移动端模板 vant

vant官网:https://vant-contrib.gitee.io/vant/#/zh-CN

配置教程:https://blog.csdn.net/q4717529/article/details/118516228

### 1.安装组件库

```cmd
 安装命令 npm install vant@next --save 
 (因为使用的是vue3.x 所以要安装vant的针对vue3的版本vant@next)

```

### 2.安装postcss

```cmd
vant的进阶使用 通过 npm install postcss postcss-pxtorem --save-dev
(用来将px尺寸转化为rem尺寸) 配置rem的根元素字体大小 安装lib-flexiable 
安装命令 npm i amfe-flexible --save 注意 这里会报错 postCss 需要8的版本
所以这里建议对postcss-pxtorem进行降级
降级命令 npm install postcss-pxtorem@5.1.1 --save

```

### 3.在main.js中引入vant组件库


```JavaScript
  import vant from 'vant'
  createApp(App).use(vant).$mount('#app)

```

### 4.新建一个[postcss](https://so.csdn.net/so/search?q=postcss&spm=1001.2101.3001.7020).config.js



在项目根目录中新建一个[postcss](https://so.csdn.net/so/search?q=postcss&spm=1001.2101.3001.7020).config.js 

```JavaScript
// postcss.config.js
module.exports = {
    plugins: {
        // postcss-pxtorem 插件的版本需要 >= 5.0.0
        'postcss-pxtorem': {
            rootValue({ file }) { // 判断是否是vant的文件 如果是就使用 37.5为根节点字体大小
            // 否则使用75 因为vant使用的设计标准为375 但是市场现在的主流设置尺寸是750
                return file.indexOf('vant') !== -1 ? 37.5 : 75;
            },
            // 配置哪些文件中的尺寸需要转化为rem *表示所有的都要转化
            propList: ['*'],
        },
    },
};

```



示例代码:

```vue
<template>
  <div class="search">
    <van-search
      v-model="value"
      shape="round"
      background="#4fc08d"
      placeholder="请输入搜索关键词"
    />
  </div>
  <!-- 不指定单位，默认使用 px -->
  <van-icon name="chat-o" size="40" />
  <!-- 指定使用 rem 单位 -->
  <van-icon name="chat-o" size="3rem" />
  <p>hello font</p>
</template>

<script>
export default {
  data() {
    return {
      value: "",
    };
  },
};
</script>

<style>
body{
  width: 1750px;
}
</style>

```

![image-20220501125530733](G:\Document\mdNote\Typora\image-20220501125530733.png)

## 26.反射机制

[反射机制](https://so.csdn.net/so/search?q=反射机制&spm=1001.2101.3001.7020)指的是程序在运行时能够获取自身的信息。例如一个对象能够在运行时知道自己有哪些方法和属性。

在JS里也有和Java反射机制类似的特性 for in，它除了用作循环，还可以体现出反射的思想。

### 1、for in用于循环

```JavaScript
var arr=[{key:'key1'},{key:'key2'}];
for(var i in arr){
    console.log(arr[i].key)
}

```

### 2、for in用于反射

```JavaScript
for(var p in obj){
      if(typeof(obj[p]=="function"){
             obj[p]();
      }else{
             alert(obj[p]);
      }
}
```

在vue中使用也一样:

```vue
    rowHandleEdit(index, row){
      for (const rowKey in row) {
        console.log("rowKey",rowKey)
        if(typeof(row[rowKey])!="function"){
          console.log("row[rowKey]: ",row[rowKey])
        }
      }
      console.log("编辑:index=",index,", row=", row);
    },
```

对象row遍历出来得rowKey是对象得字段名,而row[rowKey]则是对应得值:

![image-20220506105757558](G:\Document\mdNote\Typora\image-20220506105757558.png)



```vue
rowHandleEdit(index, row){
      this.dataInitialize();
      this.isEditInput=true;
      var rIndex=0;
      for (const rowKey in row) {
        if(typeof(row[rowKey])!="function"){
          var searchTimes=0;
          for (let i = rIndex; i < this.FieldsList.length; i++) {
            // row中的rowKey字段和FieldsList[i].prop的属性相等时,说明是同一个字段
            if (rowKey==this.FieldsList[i].prop){
              // 将row[rowKey]中的值,赋给输入框
              this.inputArr[i]=row[rowKey];
              break;
            }
            // 防止FieldsList和row字段位置不一致,就是最不保险得方法,确保全部循环一遍
            if (i >= this.FieldsList.length-1){
              if (searchTimes<this.FieldsList.length){
                i=0;
              }
            }
            // 当查找次数超过FieldsList字段得总数时,退出
            if (searchTimes>=this.FieldsList.length)
              break;
            searchTimes++;
          }
          rIndex=rIndex+1;
        }
      }
    },
```

## 27.发送post请求

```
axios({
  method: "post",
  url: fieldUrl,
  data: obj,
  transformRequest: [function (data) {
    return Qs.stringify(data) //使用Qs将请求参数序列化
  }],
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded' //必须设置传输方式
  }
}).then((res) => {
  console.log(res.data)
  if (res.data.state=="success"){
    alert("新增成功");
  }else {
    alert("新增失败");
  })
```

后端接收时:

## 28.ul 绑定多个值

当绑定的值个数不确定时:v-model="inputArr[index]" 

```
<el-input v-for="(field,index) in FieldsList" v-model="inputArr[index]"  :style="'width: '+(parseInt(field.width)+50)+'px'" :placeholder="field.label" :key="field.prop"
          type="textarea" :rows="defaultRow" :autosize="autoSize" :auto-complete="'on'">
  {{inputArr[index]}}
</el-input>
```

```
input_0:"",
input_1:"",
input_2:"",
input_3:"",
input_4:"",
input_5:"",
input_6:"",
input_7:"",
inputArr:[],
```

```
this.inputArr=[this.input_0,this.input_1,this.input_2,this.input_3,this.input_4,this.input_5,this.input_6,this.input_7];
```

## 29.使用vue ui创建项目

在cmd窗口中输入vue ui回车

![image-20220515232001827](G:\Document\mdNote\Typora\image-20220515232001827.png)

然后根据步骤,手动创建项目

![image-20220515232024648](G:\Document\mdNote\Typora\image-20220515232024648.png)



## 30.配置全局axios

### 30.1配置一个全局的工具类

```
import Vue from "vue";
import axios from "axios";

const ajax=axios.create({
    //
    baseURL:"http://localhost:9090"
});
// 挂载后,全局可以用$ajax对象
Vue.prototype.$ajax=ajax;
```

### 30.2 使用

```vue
      this.$ajax.get("/test").then(res=>{
        console.log("res:",res);
      }).catch(err=>{
        console.log("err:",err)
      });
```

## 31.axios拦截器

```vue
          this.$ajax({
            method: "post",
            url: "/back/user/login",
            data: this.form,
          }).then((res) => {// 从这里开始被拦截
            console.log(res);
          });
```

如果拦截器没有返回值, console.log(res);则拿不到任何值.



```JavaScript
import Vue from "vue";
import axios from "axios";
import { Message } from "element-ui";
import store from "@/store/index"
const ajax = axios.create({
  //
  // baseURL:"http://localhost:9090"
});
// ajax拦截器,请求
ajax.interceptors.request.use(res=>{
  console.log("请求信息: ", res);
  // 这里判断localStorage里面是否存在token，如果有则在请求头里面设置
  if (store.state.token != "") {
    // res.headers.Authorization = getLocalStorage("jwtToken");
    console.log(store.state.token);
   // 设置token认证 res.headers.Authorization=store.state.token;
  }
  // 放行,不让下面的方法拿不到参数
  return res;
});
// ajax拦截器,响应
ajax.interceptors.response.use((res) => {
  console.log(res.data);
  if (res.data.flag) {
    Message({
      message: res.data.message,
      type: "success",
    });
  } else {
    Message.error(res.data.message);
  }
  // 放行,不让下面的方法拿不到参数
  return res;
});
// 挂载后,全局可以用$ajax对象
Vue.prototype.$ajax = ajax;

```

## 32.vuex保存session

### 1.先在store/index.js中配置

```JavaScript
import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 字段,相当与vue中的data
    token: sessionStorage.getItem("token"),
  },
  mutations: {
    // 保存方法
    setToken(state, data) {
      state.token = data;
      console.log("vuex中的token: ", data);
      sessionStorage.setItem("token", data);
    },
  },
  actions: {},
  modules: {},
});

```

### 2.在有需要处使用

```vue
          this.$ajax({
            method: "post",
            url: "/back/user/login",
            data: this.form,
          }).then((res) => {
            // 从这里开始被拦截
            let token = res.data.data.token;
            let tokenHead = res.data.data.tokenHead;
			// 使用vuex
            this.$store.commit("setToken", tokenHead + token);
          });
```





## 33.配置了代理转发,还是报错

https://segmentfault.com/q/1010000022482431

很可能是后端问题:

如,请求地址虽然显示前端,但后端可能已经收到了请求,只不过是被后端拦截了.

![image-20220601215246540](../Typora/image-20220601215246540.png)



查看响应:

![image-20220601215356425](../Typora/image-20220601215356425.png)



配置白名单即可.



## 33.嵌套页面

如图,在一个vue里面嵌套另一个vue

![image-20220602153651606](../Typora/image-20220602153651606.png)



### 方式一:

F:\Java\PracticeProject\20220515个人运动管理平台\code\vue_sport\src\views\Home.vue

在一个vue文件用<router-view/>里引入其他的vue:

```vue
<template>
  <div id="app">
    <router-link to="/home"></router-link>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>
</template>
```

### ![image-20220602153925047](../Typora/image-20220602153925047.png)



## 35.管理平台页面

F:\Java\PracticeProject\20220515个人运动管理平台\code\vue_sport\src\views\Home.vue

![image-20221103100724211](../../Typora/image-20221103100724211.png)

F:\Java\PracticeProject\20220515个人运动管理平台\code\vue_sport\src\views\system\permission.vue

![image-20221103100802238](../../Typora/image-20221103100802238.png)



### 1.布局

https://element.eleme.cn/#/zh-CN/component/container

### 2.侧边栏

https://element.eleme.cn/#/zh-CN/component/menu

### 3.表格

https://element.eleme.cn/#/zh-CN/component/table

```vue
      <!--  data为需要绑定的内容 
      :header-row-style="{ height: '30px', 'line-height': '30px' }" 设置头行的汉高和line-height
      -->
      <el-table
        :data="tableData"
        style="width: 100%; margin-top: 1px; padding: 0"
        :row-class-name="tableRowClassName"
        :header-row-style="{ height: '30px', 'line-height': '30px' }"
      >
        <el-table-column prop="id" label="编号" width="180"> </el-table-column>
        <el-table-column prop="label" label="标签" width="180">
        </el-table-column>
        <el-table-column prop="code" label="代码"> </el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small"
              >修改</el-button
            >

            <el-button @click="deleteClick(scope.row)" type="text" size="small"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
```



### 4.对话框

https://element.eleme.cn/#/zh-CN/component/dialog

![image-20221103101044090](../../Typora/image-20221103101044090.png)

![image-20221103101103567](../../Typora/image-20221103101103567.png)



```vue
<el-dialog
      :visible.sync="addPerBox"
      width="300px"
      style="line-height: 10px"
    >
      <!--  设置对话框的样式  -->
      <div class="dialogS">
        <span
          style="
            margin-bottom: 10px;
            font-family: 悠哉字体;
            font-size: medium;
            text-align: center;
          "
          >增加权限</span
        >
        <el-row style="margin-top: 10px">
          <el-col :span="5" style="margin-top: 10px; font-family: 悠哉字体">
            <span>label:</span>
          </el-col>
          <el-col :span="16">
            <el-input
              v-model="addPermission.label"
              placeholder="请输入标签"
            ></el-input>
          </el-col>
        </el-row>
        <el-row style="margin: 5px 0">
          <el-col :span="5" style="margin-top: 10px; font-family: 悠哉字体">
            <span>code:</span>
          </el-col>
          <el-col :span="16">
            <el-input
              v-model="addPermission.code"
              placeholder="请输入代码"
            ></el-input>
          </el-col>
        </el-row>
        <span slot="footer" class="dialog-footer">
          <el-button
            @click="
              addPerBox = false;
              boxHandleIndex = 0;
            "
            >取 消</el-button
          >
          <el-button type="primary" @click="addPerMed">确 定</el-button>
        </span>
      </div>
    </el-dialog>
    <!--  删除弹出框  -->
    <el-dialog
      title="提示"
      :visible.sync="deletePerBox"
      width="300px"
      style="line-height: 10px"
    >
      <span style="font-family: 悠哉字体; font-size: medium; color: red"
        >确定删除{{ addPermission.code }}权限嘛?</span
      >
      <span slot="footer" class="dialog-footer">
        <el-button @click="deletePerBox = false">取 消</el-button>
        <el-button type="primary" @click="deletePerMed">确 定</el-button>
      </span>
    </el-dialog>
```

### 5.分页框

https://element.eleme.cn/#/zh-CN/component/pagination

![image-20221103101633050](../../Typora/image-20221103101633050.png)

```vue
<!--  Layout布局控件，并且分成24分栏。
       gutter: 每一栏之间的距离-->
      <el-row :gutter="10" style="line-height: 0">
        <el-col :span="15">
          <!--  input3:-->
          <el-input
            placeholder="请输入要查找的内容"
            v-model="searchStr"
            class="input-with-select"
          >
            <!-- select与value对应 -->
            <el-select v-model="searchType" slot="prepend" placeholder="请选择">
              <el-option label="未知" value="1"></el-option>
              <el-option label="未知" value="2"></el-option>
              <el-option label="未知" value="3"></el-option>
            </el-select>
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="searchPer"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button
            type="primary"
            plain
            @click="
              addPerBox = true;
              boxHandleIndex = 1;
            "
            >增加权限</el-button
          >
        </el-col>
      </el-row>
```



### 6.分页

page-sizes:

![image-20221103101802425](../../Typora/image-20221103101802425.png)

total: 

![image-20221103101824081](../../Typora/image-20221103101824081.png)





```vue
<!--  分页 Pagination  -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 6, 7, 8, 9, 10]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalC"
        style="margin-top: 10px"
      >
      </el-pagination>
```

## 34.v-show无效

https://blog.csdn.net/Study123wf/article/details/127110796

背景:在用[elementui](https://so.csdn.net/so/search?q=elementui&spm=1001.2101.3001.7020)开发表格时，想动态的控制某列的显示隐藏，发现用v-show不生效！
原因分析:通过v-show隐藏的原理是控制css的display:none;而而因为td的display: table-cell；权限高于display:none 所以用v-show不生效 只能换成v-if

![image-20221103181216725](../../Typora/image-20221103181216725.png)



## 36.传值

https://m.php.cn/vuejs/481554.html

### 1.父传子

父子组件的关系可以总结为prop向下传递，事件向上传递。父组件通过prop给子组件下发数据，子组件通过事件给父组件发送消息。

父组件：

```vue
<template>

  <p>

    父组件:

    <input type="text" v-model="name">

    <br>

    <br>

    <!-- 引入子组件 -->

    <child :inputName="name"></child>  //child子组件通过 :inputName="name" 将值传过去

  </p>

</template>

<script>

  import child from './child'

  export default {

    components: {

      child

    },

    data () {

      return {

        name: ''

      }

    }

  }

</script>
```

子组件：

```vue
<template>

  <p>

    子组件:

    <span>{{inputName}}</span>

  </p>

</template>

<script>

  export default {

    // 接受父组件的值

    props: {

      inputName: String,   //在这里对传过来的进行接收

      required: true

    }

  }

</script>
```

### 2.子传父

子组件可以通过$emit触发父组件的自定义事件。vm.$emit(event,arg) 用于触发当前实例上的事件；

子组件：

```vue
<template>

  <p>

    子组件:

    <span>{{childValue}}</span>

    <!-- 定义一个子组件传值的方法 -->

    <input type="button" value="点击触发" @click="childClick">

  </p>

</template>

<script>

  export default {

    data () {

      return {

        childValue: '我是子组件的数据'

      }

    },

    methods: {

      childClick () {

        // childByValue是在父组件on监听的方法

        // 第二个参数this.childValue是需要传的值

        this.$emit('childByValue', this.childValue)  

      }

    }

  }

</script>
```

父组件：

```vue
<template>

  <p>

    父组件:

    <span>{{name}}</span>

    <br>

    <br>

    <!-- 引入子组件 定义一个on的方法监听子组件的状态-->

    <child v-on:childByValue="childByValue"></child>

  </p>

</template>

<script>

  import child from './child'

  export default {

    components: {

      child

    },

    data () {

      return {

        name: ''

      }

    },

    methods: {

      childByValue: function (childValue) {

        // childValue就是子组件传过来的值

        this.name = childValue

      }

    }

  }

</script>
```

### 3.**非父子组件传值**

非父子组件之间传值，需要定义个公共的公共实例文件bus.js，作为中间仓库来传值，不然路由组件之间达不到传值的效果。

公共bus.js

```js
//bus.js

import Vue from 'vue'

export default new Vue()
```

组件A(发送)：

```vue
<template>

  <p>

    A组件:

    <span>{{elementValue}}</span>

    <input type="button" value="点击触发" @click="elementByValue">

  </p>

</template>

<script>

  // 引入公共的bug，来做为中间传达的工具

  import Bus from './bus.js'

  export default {

    data () {

      return {

        elementValue: 4

      }

    },

    methods: {

      elementByValue: function () {

        Bus.$emit('val', this.elementValue)

      }

    }

  }

</script>
```

组件B(接收)：

```vue
<template>

  <p>

    B组件:

    <input type="button" value="点击触发" @click="getData">

    <span>{{name}}</span>

  </p>

</template>

<script>

  import Bus from './bus.js'

  export default {

    data () {

      return {

        name: 0

      }

    },

    mounted: function () {

      var vm = this

      // 用$on事件来接收参数

      Bus.$on('val', (data) => {

        console.log(data)

        vm.name = data

      })

    },

    methods: {

      getData: function () {

        this.name++

      }

    }

  }

</script>
```



## 40.表格table

https://element.eleme.cn/#/zh-CN/component/table

```vue
      <!--  data为需要绑定的内容
      :header-row-style="{ height: '30px', 'line-height': '30px' }" 设置头行的汉高和line-height
      :cell-style="{padding:'0px'}" 设置行内的padding
      行内可以加入图片或其他,但加入后需要设置好图片的格式,不然行高会随着图片的增大而增大,就算设置了汉高也会
      -->
<el-table
        :data="tableData"
        style="width: 100%; margin-top: 1px; padding: 0"
        :row-class-name="tableRowClassName"
        :cell-style="{padding:'0px'}"
        :header-row-style="{ height: '30px', 'line-height': '30px' }"
      >
        <el-table-column prop="id" label="编号" width="50"> </el-table-column>
        <el-table-column prop="username" label="用户名" width="80">
        </el-table-column>
        <el-table-column prop="nickName" label="昵称"> </el-table-column>
        <el-table-column prop="sex" label="性别"> </el-table-column>
        <!--  表格中加入图片,需要用到template, 把prop值传入到template里面-->
        <el-table-column v-if="allShow" prop="avatar" label="用户头像">
          <template slot-scope="scope">
            <el-image
                style="max-height: 90px"
              v-show="scope.row.avatar != ''"
              :src="scope.row.avatar != '' ? scope.row.avatar : ''"
              fit="contain"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column v-if="allShow" prop="address" label="地址">
        </el-table-column>
        <el-table-column prop="openId" label="微信Id"> </el-table-column>
        <el-table-column prop="status" label="禁用"> </el-table-column>
        <el-table-column prop="admin" label="管理员"> </el-table-column>
        <el-table-column prop="phoneNumber" label="电话"> </el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small"
              >修改</el-button
            >

            <el-button @click="deleteClick(scope.row)" type="text" size="small"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
```

![image-20221109141350541](../../Typora/image-20221109141350541.png)



## 37.表格中加入图片

F:\Java\PracticeProject\20220515个人运动管理平台\code\vue_sport\src\views\system\user.vue

![image-20221108192224133](../../Typora/image-20221108192224133.png)

```vue
<!--  表格中加入图片,需要用到template, 把prop值传入到template里面-->
        <el-table-column v-if="allShow" prop="avatar" label="用户头像">
          <template slot-scope="scope">
            <el-image
              v-show="scope.row.avatar != ''"
              :src="scope.row.avatar != '' ? scope.row.avatar : ''"
              fit="contain"
            ></el-image>
          </template>
        </el-table-column>
```

## 38.el-upload 文件上传

![image-20221108192841291](../../Typora/image-20221108192841291.png)

https://element.eleme.cn/#/zh-CN/component/upload

F:\Java\PracticeProject\20220515个人运动管理平台\code\vue_sport\src\views\system\user.vue

```vue
    <!--  action: 上传地址,
    show-file-list: 是否展示列表
    list-type="picture"如果不是图片,file只有uid没有url
    :limit="1" 只上传一个文件
    :auto-upload="false" 自动上传
    :on-change="addFile" 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    on-preview	点击文件列表中已上传的文件时的钩子	function(file)
    -->
<el-upload
                class="upload-demo"
                style="height: 90px; line-height: 0; margin-top: 30px"
                ref="upload"
                :action="action"
                :headers="headers"
                :auto-upload="false"
                :multiple="false"
                :show-file-list="false"
                list-type="picture"
                :on-change="addFile"
                :on-success="uploadSuccess"
              >
                <el-button slot="trigger" size="small" type="primary"
                  >选择头像</el-button
                >
              </el-upload>

 字段:
 action: "/back/tool/upload",
      headers: {
        Authorization: sessionStorage.getItem("token"),
      },
方法:
// 上传图片
    submitUpload() {
      this.$refs.upload.submit();
    },
    // 上传图片成功
    uploadSuccess(response, file, fileList) {
      // this.imageUrl = this.domainName + response.data;
      // this.avatarShow = true;
      console.log(response);
      // 接收服务器返回的图片链接,不需要加域名
      this.addUser.avatar = response.data;
      console.log("imageurl: ", this.domainName + this.addUser.avatar);
      this.insertOrUpdateUser();

      this.$refs.upload.clearFiles();
      this.uploadImage = null;
    },
    // 添加图片,修改图片
    addFile(file, fileList) {
      // fileList 为上传的文件,因为只需要一个头像,所以fileList的length始终为1
      // https://www.php.cn/vuejs/483459.html :this.$delete(fileList, 1);这样删能把长度也删掉,而不是简单的赋值为null
      this.uploadImage = file;
      // 先将前端的url给avatar
      this.addUser.avatar = file.url;
      // console.log("file: ", file);
      // console.log("fileList: ", fileList);
      if (fileList.length > 1) {
        fileList[0] = fileList[1];
        this.$delete(fileList, 1);
      }
    },
```

### 注意:

1.如果找不到文件的url,则设置list-type="picture"

2.多使用:on-change="addFile"

 :on-change="addFile" 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用

3.返回信息在:on-success="uploadSuccess"里面,

// 上传图片成功
    uploadSuccess(response, file, fileList) {
      // this.imageUrl = this.domainName + response.data;
      // this.avatarShow = true;
      console.log(response);
      // 接收服务器返回的图片链接,不需要加域名
      this.addUser.avatar = response.data;
      console.log("imageurl: ", this.domainName + this.addUser.avatar);
      this.insertOrUpdateUser();

      this.$refs.upload.clearFiles();
      this.uploadImage = null;
    },

## 39.数组删除

https://www.php.cn/vuejs/483459.html 

### 1.数组删除的三种方式:

#### 1.delete

**delete**只是被删除的元素变成了 empty/undefined 其他的元素的**键值还是不变**。

```vue
//delete 数组[序号]
delete a[0];
```

#### 2.splice

**splice**直接删除了数组 **改变了数组的键值**。

```vue
// 数组.splice(序号, 数量)
a.splice(1,1)
```

#### 3.**Vue.delete**

**Vue.delete**直接删除了数组 **改变了数组的键值**。

```vue
// this.$delete(数组, 序号)
this.$delete(a,1)

```

![image-20221108193502263](../../Typora/image-20221108193502263.png)

![img](https://img.php.cn/upload/article/000/000/024/f93e7a1e1a5d98e2990fa44c7d1ed65f-0.png)



## 40.打包

.env环境配置

https://blog.csdn.net/qq_39275868/article/details/121212229

https://cloud.tencent.com/developer/article/2025902

直接: npm run build即可

## 41.created不刷新

https://blog.csdn.net/weixin_43498992/article/details/118894811

情况: 因为网页默认进入的是主页,但主页要判断有没有登录,第一次肯定没有登录,所以虽然created了,但是没有获取到初始化信息.

从登录页面进入主页后,又因为已经created了一次,不会再执行了.

所以使用activated钩子代替created:

```vue
activated() {
    this.getList();
  },

```



## 42.npm install 无效时

使用淘宝镜像

npm install -g cnpm --registry=https://registry.npm.taobao.org

 cnpm install marked --save

![image-20221114173512166](../../Typora/image-20221114173512166.png)



## 43.lessloader 报错

https://blog.csdn.net/weixin_55959870/article/details/123149955

遇到Module not found:Error:Can t resolve lessloader 问题。

把less注销掉即可:

![image-20221114173417300](../../Typora/image-20221114173417300.png)







