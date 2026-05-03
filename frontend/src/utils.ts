export function formatPoints(points: number): string {
  const lastTwo = points % 100
  const lastOne = points % 10

  let word
  if (lastTwo >= 11 && lastTwo <= 14) {
    word = 'баллов'
  } else if (lastOne === 1) {
    word = 'балл'
  } else if (lastOne >= 2 && lastOne <= 4) {
    word = 'балла'
  } else {
    word = 'баллов'
  }

  return `${points} ${word}`
}
