import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'
import qs from 'qs'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 15000, // 请求超时时
})

// request拦截器
service.interceptors.request.use(config => {
  if (store.getters.token) {
    config.headers['alliance-token'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  if (config.method === 'post') {
    config.data = qs.stringify(config.data) // 序列化post请求数据
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    /**
     * code为非2000是抛错 可结合自己业务进行修改
     * 2000->请求成功,5000->服务器错误,4000->请求参数错误,4001->未授权,4002->用户未登录 4003->kickout
     *
     */
    const res = response.data
    if (res.code !== '2000') {
      Message({
        message: res.errorMsg,
        type: 'error',
        duration: 2 * 1000
      })

      // 4002->用户未登录
      if (res.code === '4002' || res.code === '4003') {
        MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        })
      }

      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 2 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
