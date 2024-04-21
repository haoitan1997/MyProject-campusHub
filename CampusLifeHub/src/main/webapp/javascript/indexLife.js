window.onload = function (){
    var request = new XMLHttpRequest();
    request.onreadystatechange = function (){
        if(request.readyState == 4){
            if(request.status == 200){
                var response = this.responseText;
                // console.log(response)
                response = JSON.parse(response)
                let dataList = response.data
                // console.log(dataList)
                let user = dataList.userInfo
                var article = dataList.articleInfos
                var role = dataList.role
                var touxiang
                var isSingle
                if(user.sex == 0){
                    user.sex = '女'
                }else if(user.sex == 1){
                    user.sex = '男'
                }else{
                    user.sex = '待编辑'
                }
                if(user.alone == 0){
                    user.alone = '否'
                }else if(user.alone == 1){
                    user.alone = '是'
                }else{
                    user.alone = '待编辑'
                }
                if(user.age == null){
                    user.age = '待编辑'
                }

                let userElement =
                `
                    <div class="main-right-top">
                        <img src="image/advator/${role.avator}">
                    </div>
                    <div class="main-right-person">
                        <div>
                            <span>角色名称：${role.roleName}</span>
                        </div>
                        <div>
                            <span>性别：${user.sex}</span>
                        </div>
                        <div>
                            <span>年龄：${user.age}</span>
                        </div>
                        <div>
                            <span>是否单身：${user.alone}</span>
                        </div>
                        <div id="edit" class="edit">
                            编辑个人信息
                        </div>
                        <div class="fabiao">
                            说点什么吧~
                        </div>
                    </div>
                `
                let mainRight = document.getElementById("main-right")
                mainRight.innerHTML = userElement
                let five = article.splice(0,5);
                console.log(five)

                for(let i = 0;i < five.length;i++){
                    if(five[i].alone == 0){
                        isSingle = 'iconfont icon-shuangren'
                    }else{
                        isSingle = 'iconfont icon-geren'
                    }
                    if(five[i].sex == 0){
                        touxiang = '#icon-xingbie-nv'
                    }else{
                        touxiang = '#icon-xingbie-nan'
                    }

                    let mainbottomtemplate = document.getElementById("main-bottom-template");
                    var ima = five[i].image.split(",")
                    let articleElement =
                        `
                    <div class="main-bottom">
                        <div class="main-bottom-bolg">
                            <div class="title">
                                <div class="title-img">
                                    <img src="image/advator/${five[i].avator}"/>
                                </div>
                                <div class="title-name">
                                    <div class="title-name-top">
                                        <span>${five[i].roleName}</span>
                                        <svg class="icon" aria-hidden="true">
                                            <use xlink:href="${touxiang}"></use>
                                          </svg>
                                        <span class="${isSingle}"></span>
                                        <span class="iconfont icon-nianling"></span>
                                        <span>${five[i].age}</span>
                                    </div>
                                    <div class="title-name-time">
                                        ${five[i].createTime}
                                    </div>   
                                </div>
                            </div>
                            <div class="blog">
                                <div class="text">
                                    ${five[i].content}
                                </div>
                                <div class="picture">
                        `
                                    let articleElement1 = ""
                                    for(let i = 0;i < ima.length;i++){
                                        let aa =
                                        `
                                        <div>
                                            <img src="/aaa/upload/${ima[i]}">
                                        </div>
                                        `
                                        articleElement1 += aa
                                    }
                        let articleElement2 =
                        `            
                                </div>                         
                                <div class="share">
                                    <div class="icon icon-talk">
                                        <span class="iconfont icon-pinglun">${five[i].comments}</span>
                                    </div>
                                    <div class="icon icon-zan">
                                        <span class="iconfont icon-dianzan">${five[i].dianzan}</span>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    `
                    mainbottomtemplate.innerHTML += (articleElement + articleElement1 + articleElement2)
                }
                //编辑个人信息
                var tanchuang = document.getElementById("tanchuang")

                const edit = document.querySelector(".edit")
                // console.log(edit)
                edit.addEventListener("click",function(){
                    tanchuang.style.display = "block"
                })

                var guanbi = document.getElementById("guanbi")
                guanbi.addEventListener("click",function(){
                    tanchuang.style.display = "none"
                })

                var submit = document.getElementById("submit")
                submit.addEventListener("click",function(){
                    var sexValue = null
                    var sex = document.getElementsByName("sex")
                    for(var i = 0;i < sex.length;i++){
                        if(sex[i].checked){
                            sexValue = sex[i].value
                        }
                    }
                    var isAlone = null
                    var alone = document.getElementsByName("isAlone")
                    for(var i = 0;i < alone.length;i++){
                        if(alone[i].checked){
                            isAlone = alone[i].value
                        }
                    }
                    var age = document.getElementById("age_value").value
                    //获取数据后发送ajax请求
                    var quest = new XMLHttpRequest()
                    quest.onreadystatechange = function(){
                        if(this.readyState == 4){
                            if(this.status == 200){
                                tanchuang.style.display = "none"
                                window.location.href="indexLife.html"
                            }
                        }
                    }
                    quest.open("post","user/submitUserInfo",true)
                    quest.setRequestHeader("Content-type","application/x-www-form-urlencoded")
                    quest.send("sex="+sexValue+"&isAlone="+isAlone+"&age="+age)
                })

                //说点什么吧~
                var textarea = document.querySelector("textarea")
                textarea.addEventListener("input",function(e){
                    textarea.style.height = '90px';
                    textarea.style.height = e.target.scrollHeight + 'px';
                })
                //添加图片
                const img = document.querySelector(".tupian")
                let input = document.createElement("input")
                const pict = document.querySelector(".pict")
                input.type = "file"
                input.name = "fileup"
                var files = []
                var idx = 0;
                // console.log(input)
                img.addEventListener("click",function(){
                    input.click()
                })
                input.addEventListener("change",function(e){
                    files[idx] = e.target.files[0]
                    let url = URL.createObjectURL(e.target.files[0])
                    let imgElement =
                        `
                        <div class="dele">
                            <span id="${idx}" class="iconfont icon-guanbi" name="guanbi"></span>
                            <img src="${url}">
                        </div>
                        `
                    pict.innerHTML += imgElement
                    idx++
                })

                document.getElementById('pict').addEventListener('click', function (ev) {//删除对应的图片
                    var target = ev.target || ev.srcElement;
                    if (target.nodeName.toLowerCase() == 'span') {
                        // var e = document.getElementById(target.parentNode.id);
                        // document.getElementById("joblist").removeChild(e);
                        files[target.id] = -1
                        var node = target.parentNode
                        var node1 = node.parentNode
                        node1.removeChild(node)
                    }
                });
                //快捷发布弹框与发送数据
                var shuoshuo = document.getElementById("shuoshuo")
                const fabiao = document.querySelector(".fabiao")
                fabiao.addEventListener("click",function (){
                    shuoshuo.style.display = "block"
                })
                var guanbi1 = document.getElementById("guanbi1")
                guanbi1.addEventListener("click",function (){
                    shuoshuo.style.display = "none"
                })
                var fasong = document.getElementById("fasong")

                fasong.addEventListener("click",function(){
                    var formdata = new FormData()
                    var textValue = document.getElementById("wenben").value
                    for(var i = 0;i < files.length;i++){
                        if(files[i] !== -1){
                            formdata.append("files", files[i])
                        }
                    }
                    formdata.append("text",textValue)
                    //获取数据后发送ajax请求
                    var quest = new XMLHttpRequest()
                    quest.onreadystatechange = function(){
                        if(this.readyState == 4){
                            if(this.status == 200){
                                shuoshuo.style.display = "none"
                                window.location.href="indexLife.html"
                            }
                        }
                    }
                    quest.open("post","article/fabiao",true)
                    // quest.setRequestHeader("Content-Type","multipart/form-data")
                    quest.send(formdata)
                })

            }
        }
    }
    request.open("get","user/dayLife",true)
    request.send()




}