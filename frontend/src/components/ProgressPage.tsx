import { useQuery } from '@tanstack/react-query'
import { getProgress } from '../api/progress'
import { Progress } from '@gravity-ui/uikit'
import { Chart, type ChartData } from '@gravity-ui/charts'

export function ProgressPage() {
  const { data: progress } = useQuery({ queryKey: ['progress'], queryFn: getProgress })

  if (!progress) return null

  const tasksTotal = progress.testTasksTotal + progress.mistakesTasksTotal + progress.openTasksTotal
  const tasksSolved =
    progress.testTasksSolved + progress.mistakesTasksSolved + progress.openTasksSolved

  const pointsPercentage = Math.round((progress.pointsEarned / progress.pointsTotal) * 100)

  const chartData = [
    { name: 'Тестовые задания', value: progress.testTasksSolved },
    { name: 'Поиск ошибок', value: progress.mistakesTasksSolved },
    { name: 'Открытые задания', value: progress.openTasksSolved },
  ]

  const chartConfig: ChartData = {
    series: {
      data: [
        {
          type: 'pie',
          data: chartData,
          innerRadius: '50%',
        },
      ],
    },
    legend: {
      enabled: true,
      position: 'bottom',
    },
  }

  return (
    <div>
      <Progress
        theme="success"
        value={pointsPercentage}
        text={`Всего баллов: ${progress.pointsEarned} из ${progress.pointsTotal} (${pointsPercentage}%)`}
      />
      <div className="mt-4 grid grid-cols-2 gap-2">
        <ul className="mt-12 leading-6">
          <li>
            Решено заданий: {tasksTotal}/{tasksSolved} (
            {Math.round((tasksSolved / tasksTotal) * 100)}
            %)
          </li>

          <li>
            Тестовые задания: {progress.testTasksSolved}/{progress.testTasksTotal} (
            {Math.round((progress.testTasksSolved / progress.testTasksTotal) * 100)}%)
          </li>

          <li>
            Поиск ошибок: {progress.mistakesTasksSolved}/{progress.mistakesTasksTotal} (
            {Math.round((progress.mistakesTasksSolved / progress.mistakesTasksTotal) * 100)}%)
          </li>

          <li>
            Открытые задания: {progress.openTasksSolved}/{progress.openTasksTotal} (
            {Math.round((progress.openTasksSolved / progress.openTasksTotal) * 100)}%)
          </li>
        </ul>

        <div className="h-54 w-full">
          <Chart data={chartConfig} />
        </div>
      </div>
    </div>
  )
}
