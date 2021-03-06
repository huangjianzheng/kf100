import request from '@/utils/request'

const baseUrl = '/admin/banner/';


export function getBannerList(current,pageSize) {
  return request({
    url: baseUrl + '/list',
    method: 'post',
    data: {
      type:1,
      current:current,
      pageSize:pageSize
    }
  })
}

export function getExpertList(current,pageSize) {
  return request({
    url: baseUrl + '/list',
    method: 'post',
    data: {
      type:2,
      current:current,
      pageSize:pageSize
    }
  })
}

export function updateBanner(banner) {
  return request({
    url: baseUrl + '/update',
    method: 'post',
    data: banner
  })
}
export function deleteBannerById(id) {
  return request({
    url: baseUrl + '/delete',
    method: 'post',
    data: {
      id:id
    }
  })
}

export function newBanner(banner) {
  return request({
    url: baseUrl + '/new',
    method: 'post',
    data: banner
  })
}


export function publish(id) {
  return request({
    url: baseUrl + '/updateStatus',
    method: 'post',
    data: {
      id: id,
      status: 1
    }
  })
}

export function revoke(id) {
  return request({
    url: baseUrl + '/updateStatus',
    method: 'post',
    data: {
      id: id,
      status: 0
    }
  })
}




