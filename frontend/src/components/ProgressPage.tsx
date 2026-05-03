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

  function calculatePercentage(value: number, total: number): number {
    return total === 0 ? 0 : Math.round((value / total) * 100)
  }

  const pointsPercentage = calculatePercentage(progress.pointsEarned, progress.pointsTotal)

  const chartData = [
    { name: 'Тестовые задания', value: progress.testTasksSolved },
    { name: 'Поиск ошибок', value: progress.mistakesTasksSolved },
    { name: 'Открытые задания', value: progress.openTasksSolved },
  ]

  const hasSolvedTasks = chartData.some((item) => item.value > 0)

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
            {calculatePercentage(tasksSolved, tasksTotal)}
            %)
          </li>

          <li>
            Тестовые задания: {progress.testTasksSolved}/{progress.testTasksTotal} (
            {calculatePercentage(progress.testTasksSolved, progress.testTasksTotal)}%)
          </li>

          <li>
            Поиск ошибок: {progress.mistakesTasksSolved}/{progress.mistakesTasksTotal} (
            {calculatePercentage(progress.mistakesTasksSolved, progress.mistakesTasksTotal)}%)
          </li>

          <li>
            Открытые задания: {progress.openTasksSolved}/{progress.openTasksTotal} (
            {calculatePercentage(progress.openTasksSolved, progress.openTasksTotal)}%)
          </li>
        </ul>

        <div className="h-54 w-full">
          {hasSolvedTasks ? (
            <Chart data={chartConfig} />
          ) : (
            <div className="pt-20 text-center text-gray-500">Пока нет решённых заданий</div>
          )}
        </div>
      </div>
    </div>
  )
}
