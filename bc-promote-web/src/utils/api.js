//引入 axios
import axios from 'axios'
//引入 element-ui 的错误提示信息组件
import {Message} from 'element-ui';
//引入 router
import router from "../router";


axios.interceptors.request.use(config => {
    //判断如果存在token，请求携带这个token
    if (window.sessionStorage.getItem('token')) {
        //让拦截的请求携带token，放在请求体 Authorization 中
        config.headers['token'] = window.sessionStorage.getItem(('token'));
    }
    return config;
}, error => {
    console.log(error);
})


//response（响应） interceptors （拦截器）  处理不同情况的响应和请求，统一处理，
//success => 并不是响应成功，只是说成功的访问到了接口，需要获取响应码进行相应的处理
axios.interceptors.response.use(success => {
    if (success.status && success.status === 200) {
        if (success.data.code ==40001) {
            Message.error({message: success.data.message});
            return null;
        }else if (success.data.code ==40002) {
            Message.error({message: '请重新登录！'});
            router.replace('/')
        }else if (success.data.code ==40003) {
            Message.error({message: success.data.message});
            return null;
        }else if(success.data.code ==40004){
            Message.error({message: success.data.message});
            return null;
        }
    }
    return success.data;
}, error => {
    if (error.response.code === 504 || error.code === 404) {
        Message.error({message: "服务器挂了，囧"});
    } else if (error.response.code === 403 || error.response.data.code === 40002) {
        Message.error({message: "请重新登陆！"});
        router.replace('/')
    } else if (error.response.code === 401) {
        Message.error({message: '请先登录！'});
        router.replace('/')
    }
    return null;
});


//如果接口写好了还需要在url前面或者后面加一个路径
let base = 'http://localhost:8090/promote-restful';

//处理请求，
// export const 出口 ， postRequest 是方法名 ， url,params ：表示请求需要路径，请求需要参数
export const postRequest = (url, params) => {
    //请求后端接口
    return axios({
        //请求方法 post 方法
        method: 'post',
        //请求路径
        url: `${base}${url}`,
        //数据
        data: params
    })
}
//封装delete请求
export const deleteRequest = (url, params) => {
    //请求后端接口
    return axios({
        //请求方法 delete 方法
        method: 'delete',
        //请求路径
        url: `${base}${url}`,
        //数据
        data: params
    })
}
//封装get请求
export const getRequest = (url, params) => {
    //请求后端接口
    return axios({
        //请求方法 get 方法
        method: 'get',
        //请求路径
        url: `${base}${url}`,
        //数据
        data: params
    })
}
//封装put请求
export const putRequest = (url, params) => {
    //请求后端接口
    return axios({
        //请求方法 put 方法
        method: 'put',
        //请求路径
        url: `${base}${url}`,
        //数据
        data: params
    })
}