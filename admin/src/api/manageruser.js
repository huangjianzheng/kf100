import request from '@/utils/request'

const baseUrl = "admin/manager";

export function getManagerList(keywords, current, pageSize) {
  return request({
    url: baseUrl + '/list',
    method: 'post',
    data: {
      keywords,
      current,
      pageSize
    }
  })
}

export function newManagerUser(userName, account, passWord, roleId) {
  return request({
    url: baseUrl + '/new',
    method: 'post',
    data: {
      userName,
      account,
      passWord,
      roleId
    }
  })
}
export function updateManagerUser(userId,userName, passWord, roleId) {
  return request({
    url: baseUrl + '/update',
    method: 'post',
    data: {
      userId,
      userName,
      passWord,
      roleId
    }
  })
}
