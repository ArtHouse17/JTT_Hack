import { NavLink, Outlet, useNavigate } from 'react-router'
import { Icon, User } from '@gravity-ui/uikit'
import { Avatar, Text, DropdownMenu } from '@gravity-ui/uikit'
import { Person, ArrowRotateRight, ArrowRightFromSquare } from '@gravity-ui/icons'
import { getUserInfo } from './api/users'
import { resetProgress } from './api/progress'
import { logout } from './api/auth'
import { useQuery } from '@tanstack/react-query'

export function App() {
  const navigate = useNavigate()

  const { data: userInfo } = useQuery({ queryKey: ['userInfo'], queryFn: getUserInfo })

  if (!userInfo) return null

  return (
    <main className="mx-auto max-w-xl">
      <header className="flex justify-between py-5">
        <nav className="flex items-center gap-4">
          <NavLink to="/" className={({ isActive }) => (isActive ? 'font-bold' : '')}>
            Задания
          </NavLink>
          <NavLink to="/progress" className={({ isActive }) => (isActive ? 'font-bold' : '')}>
            Прогресс
          </NavLink>
        </nav>

        <div className="flex items-center gap-3">
          <User avatar={<Avatar icon={Person} size="xs" />} name={userInfo.username} size="s" />

          <DropdownMenu
            items={[
              {
                action: () => {
                  resetProgress()
                  window.location.reload()
                },
                iconStart: <Icon data={ArrowRotateRight} />,
                text: <Text>Сбросить прогресс</Text>,
              },
              {
                action: () => {
                  logout()
                  navigate('/login')
                },
                iconStart: <Icon data={ArrowRightFromSquare} color="danger" />,
                text: <Text color="danger">Выйти</Text>,
              },
            ]}
          />
        </div>
      </header>
      <Outlet />
    </main>
  )
}
