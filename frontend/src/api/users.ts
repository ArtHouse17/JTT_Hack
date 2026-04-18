import { USER_INFO_EXAMPLE } from './examples'

export type UserInfo = {
  username: string
}

/** GET /users/me */
export async function getUserInfo(): Promise<UserInfo> {
  return USER_INFO_EXAMPLE
}
