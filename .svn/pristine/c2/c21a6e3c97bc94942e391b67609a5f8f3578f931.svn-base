import request from '@/utils/request'

const baseUrl = "admin/article";


export function getTopLevelCategoryList() {
  return request({
    url: baseUrl + '/category/list',
    method: 'post',
    data: {
      level: 1
    }
  })
}

export function getSecondLevelCategoryListByParentId(parentId) {
  return request({
    url: baseUrl + '/category/list',
    method: 'post',
    data: {
      level: 2,
      parentId:parentId
    }
  })
}
