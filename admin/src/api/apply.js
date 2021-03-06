import request from '@/utils/request'

const baseUrl = '/admin/apply/';


export function getApplyList(searchCondition) {
  return request({
    url: baseUrl + '/list',
    method: 'post',
    data: searchCondition
  })
}


export function pass(id) {
  return request({
    url: baseUrl + '/updateStatus',
    method: 'post',
    data: {
      id: id,
      status: 1
    }
  })
}


export function unpass(id) {
  return request({
    url: baseUrl + '/updateStatus',
    method: 'post',
    data: {
      id: id,
      status: 2
    }
  })
}
