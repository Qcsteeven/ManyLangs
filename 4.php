<?php

function DFT($arr) {
    $N = count($arr);
    $out = [];
    for ($k = 0; $k < $N; $k++) {
        $re = 0;
        $im = 0;
        for ($n = 0; $n < $N; $n++) {
            $ang = 2 * M_PI * $k * $n / $N;
            $re += $arr[$n] * cos($ang);
            $im -= $arr[$n] * sin($ang);
        }
        $out[] = sqrt($re * $re + $im * $im) / $N;
    }
    return $out;
}

// Параметры сигнала
$sigs = [
    [[100], [1]],
    [[100, 300, 700], [1, 1, 1]],
    [[100, 300, 700], [3, 2, 1]]
];

$sr = 10000;
$res = [];

for ($i = 0; $i < count($sigs); $i++) {
    $f = $sigs[$i][0];
    $a = $sigs[$i][1];

    // Генерация сигнала
    $x = [];
    $n = $sr * 1.0;
    for ($j = 0; $j < $n; $j++) {
        $t = $j / $sr;
        $val = 0;
        for ($k = 0; $k < count($f); $k++) {
            $val += $a[$k] * cos(2 * M_PI * $f[$k] * $t);
        }
        $x[] = $val;
    }

    // DFT без шума
    $sp1 = DFT($x);

    // Добавим шум
    for ($j = 0; $j < count($x); $j++) {
        $x[$j] += ((mt_rand() / mt_getrandmax()) - 0.5);
    }

    // DFT с шумом
    $sp2 = DFT($x);

    $res[] = [$sp1, $sp2];
}

// Сохраняем в CSV
$f = fopen("all_spectra.csv", "w");

// Заголовок
$head = ['Частота'];
for ($i = 0; $i < count($res); $i++) {
    $head[] = "Сигнал $i";
    $head[] = "Сигнал $i + шум";
}
fputcsv($f, $head, ",", '"', "\\");

// Максимальная длина спектра
$max = 0;
for ($i = 0; $i < count($res); $i++) {
    $len1 = count($res[$i][0]);
    $len2 = count($res[$i][1]);
    if ($len1 > $max) $max = $len1;
    if ($len2 > $max) $max = $len2;
}

// Строки
for ($i = 0; $i < $max; $i++) {
    $row = [$i];
    for ($j = 0; $j < count($res); $j++) {
        $row[] = isset($res[$j][0][$i]) ? $res[$j][0][$i] : '';
        $row[] = isset($res[$j][1][$i]) ? $res[$j][1][$i] : '';
    }
    fputcsv($f, $row, ",", '"', "\\");
}

fclose($f);

echo "Сохранено в all_spectra.csv\n";
?>
