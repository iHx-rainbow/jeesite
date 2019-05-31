done
---
- 创建地图
- 缩放滚动
- 添加地图标记marker
- 添加控制条
- 定位、add、remove marker
- 指定地点缩放
---
- 创建JSON
- 选择、读取JSON
- 通过JSON动态生成marker
- 新建复选框栏
- 动态生成、删除复选框、对应label
- 复选框监听事件
- 复选框click事件与marker动态绑定 实现显示、隐藏同步
---
- 生成外部JSON文件
- Ajax读取外部JSON文件
- 添加jQuery CDN
- 获取JSON、实现异步请求
- 点击checkbox实现地点缩放，给checkbox绑定两个点击事件，stopPropagation阻止事件冒泡执行

working
---
- marker点击气泡自定义内容
- JSON字段信息与项目同步
- 阿里JSON工具 自动生成JSON
- 调整JS，提高页面加载速度

problems
---
- 引用图片仅能使用线上图片，本地图片报错
---
- 地区范围标示，可以在地图上用多点生成多边形，但是精细的地区描边需要的点太多了，还没找出浙江天地图中显示行政区范围的api
- 可以实现单个marker自定义图标，但是因为marker是通过经纬度和类型信息自动生成的，api自动生成的marker没有id唯一标识，不知道怎么实现多个marker自定义样式和方法
---
- Ajax本地跨域报错，Chrome不支持本地的异步请求，直接通过file://访问文件会报错，改成同步也报错 --allow-file-access-from-files
