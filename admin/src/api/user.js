import request from '@/utils/request'
const baseUrl = "/admin/user/";

export function getAllUser(userName,account,email,current,size) {
    return request({
        url: baseUrl + 'list',
        method: 'post',
        data: {
            userName,
            account,
            email,
            current,
            size
        }
    })
}

export function getUserCompany(id){
    return request({
        url: baseUrl + 'getUserCompany',
        method: 'post',
        data: {
            userId : id
        }
    })
}

export function getUserInfo(id){
    return request({
        url: baseUrl + 'getUser',
        method: 'post',
        data: {
            userId : id
        }
    })
}