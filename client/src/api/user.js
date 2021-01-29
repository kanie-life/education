import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/admin/userManage/login.do',
    method: 'post',
    data: {
      'loginAccount': username,
      'password': password
    }
  })
}

export function getInfo() {
  return request({
    url: '/admin/userManage/info.do',
    method: 'get',
  })
}

export function logout() {
  return request({
    url: '/admin/userManage/logout.do',
    method: 'post'
  })
}

