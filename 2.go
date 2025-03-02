package main

import (
	"fmt"
	"math/rand"
	"time"
)

func printarray(arr []int) {
	for i := 0; i < len(arr); i++ {
		fmt.Print(arr[i], " ")
	}
	fmt.Println()
}

func createStepDiv2(size int) []int {
	fmt.Println("Деление на 2:")
	start := size / 2
	var steps []int
	for i := start; i > 1; i = (i + 1) / 2 {
		steps = append(steps, i)
	}
	steps = append(steps, 1)
	printarray(steps)
	return steps
}

func createStepDiv3(size int) []int {
	fmt.Println("Деление на 3:")
	start := size / 3
	var steps []int
	for i := start; i > 1; i = (i + 1) / 3 {
		steps = append(steps, i)
	}
	steps = append(steps, 1)
	printarray(steps)
	return steps
}

func createStepDivPow2(size int) []int {
	fmt.Println("Возведение в степень 2:")
	var steps []int
	for i := 1; i < size; i = (i << 1) - 1 {
		steps = append(steps, i)
		i++
	}
	for i, j := 0, len(steps)-1; i < j; i, j = i+1, j-1 {
		steps[i], steps[j] = steps[j], steps[i]
	}
	printarray(steps)
	return steps
}

func sorted(types string, arr []int) ([]int, int) {
	var steps []int
	switch types {
	case "div2":
		steps = createStepDiv2(len(arr))
	case "div3":
		steps = createStepDiv3(len(arr))
	case "pow2":
		steps = createStepDivPow2(len(arr))
	}
	iterations := 0
	for _, step := range steps {
		for i := step; i < len(arr); i++ {
			temp := arr[i]
			j := i
			for j >= step && arr[j-step] > temp {
				arr[j] = arr[j-step]

				j -= step
			}
			arr[j] = temp
			iterations++
		}
	}
	return arr, iterations
}
func createRandomArray(size int) []int {
	rand.Seed(time.Now().UnixNano())
	arr := []int{}
	for i := 0; i < size; i++ {
		arr = append(arr, rand.Intn(1000))
	}
	return arr
}

func bubbleSort(arr []int) ([]int, int) {
	iterations := 0
	for i := 0; i < len(arr)-1; i++ {
		for j := 0; j < len(arr)-i-1; j++ {
			iterations++
			if arr[j] > arr[j+1] {
				arr[j], arr[j+1] = arr[j+1], arr[j]
			}
		}
	}
	return arr, iterations
}

func test(types string, size ...int) {
	var arr []int
	if len(size) == 0 {
		arr = []int{9, 8, 7, 6, 5, 4, 3, 2, 1}
	} else {
		arr = createRandomArray(size[0])
	}

	startTime := time.Now()
	arr, iterations := sorted(types, arr)
	elapsedTime := time.Since(startTime)
	fmt.Printf("Размер массива: %d\n", len(arr))
	fmt.Printf("Количество итераций: %d\n", iterations)
	fmt.Printf("Время выполнения сортировки: %s\n", elapsedTime)
}
func test2(types string) {
	var arr []int
	arr = createRandomArray(100000)
	startTime := time.Now()
	arr, iterations := sorted(types, arr)
	elapsedTime := time.Since(startTime)
	fmt.Printf("Размер массива: %d\n", len(arr))
	fmt.Printf("Количество итераций: %d\n", iterations)
	fmt.Printf("Время выполнения сортировки: %s\n", elapsedTime)
}

func test3() {
	arr := createRandomArray(100000)
	arr2 := append([]int{}, arr...)
	startTime := time.Now()
	arr, iterations := sorted("div3", arr)
	elapsedTime := time.Since(startTime)
	fmt.Println("Текущая сортировка:")
	fmt.Printf("Размер массива: %d\n", len(arr))
	fmt.Printf("Количество итераций: %d\n", iterations)
	fmt.Printf("Время выполнения сортировки: %s\n", elapsedTime)
	startTime = time.Now()
	arr2, iterations = bubbleSort(arr)
	elapsedTime = time.Since(startTime)
	fmt.Println("Сортировка пузырьком:")
	fmt.Printf("Размер массива: %d\n", len(arr2))
	fmt.Printf("Количество итераций: %d\n", iterations)
	fmt.Printf("Время выполнения сортировки: %s\n", elapsedTime)

}

func main() {
	fmt.Println("Сравнение кол-ва итераций на разных размерах:")
	test("div3")
	test("div3", 10)
	test("div3", 100)
	test("div3", 1000)
	fmt.Println()
	fmt.Println("Сравнение кол-ва итераций c разными шагами:")
	test2("div2")
	test2("div3")
	test2("pow2")
	fmt.Println()
	fmt.Println("Сравнение кол-ва итераций для разных сортировок:")
	test3()
}
