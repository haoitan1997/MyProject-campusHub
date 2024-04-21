const wrapper = document.querySelector(".wrapper");
const login_link = document.querySelector(".login-link");
const register_link = document.querySelector('.register-link');


register_link.addEventListener('click',function (){
    wrapper.classList.add("active");
});

login_link.addEventListener("click",()=> {
    wrapper.classList.remove("active");
});

//发送ajax请求  用户注册
var result;
const btn = document.querySelector(".btn02");
btn.addEventListener("click",()=>{
    var username = document.getElementById("phoneNumber").value;
    var password = document.getElementById("password").value;
    if(username == "" || password == ""){
        alert("用户名或密码不能为空！")
    }else{
        var request = new XMLHttpRequest();
        request.onreadystatechange = function (){
            if(this.readyState == 4){
                if(this.status == 200){
                    result = this.responseText;
                    var res = JSON.parse(result)
                    document.getElementById("message").innerHTML = res.message
                }
            }
        }
        request.open("post","user/register",true)
        request.setRequestHeader("Content-type","application/x-www-form-urlencoded")
        request.send("username="+username+"&password="+password)
    }
})



//发送ajax请求：用户登录
var result01;
const btn01 = document.querySelector(".btn01")
btn01.addEventListener("click",()=>{
    var username = document.getElementById("user").value;
    var password = document.getElementById("pw").value;
    console.log(username)
    console.log(password)
    if(username == "" || password == ""){
        alert("用户名或密码不能为空！")
    }else{
        var request = new XMLHttpRequest();
        request.onreadystatechange = function (){
            if(this.readyState == 4){
                if(this.status == 200){
                    result01 = this.responseText
                    var res = JSON.parse(result01)
                    if(res.code != "200"){
                        document.getElementById("message1").innerHTML = res.message
                    }else{
                        window.location.href="indexLife.html"
                    }

                }
            }
        }
        request.open("post","user/loginAndGetRole",true)
        request.setRequestHeader("Content-type","application/x-www-form-urlencoded")
        request.send("user="+username+"&pw="+password)
    }

})