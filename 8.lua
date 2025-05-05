function zero_protector(func)
    return function(a, b)
        if b == 0 then
            return "ALARM: деление на ноль"
        end
        return func(a, b)
    end
end

function div(a, b)
    return string.format("%.2f поделить на %.2f равно %.2f", a, b, a / b)
end

log = {}

function logger(func)
    return function(...)
        local args = {...}
        local result = func(table.unpack(args))
        
        table.insert(log, {
            params = args,
            result = result,
            date = os.date()
        })
        
        return result
    end
end

lp_div = logger(zero_protector(div))

print(lp_div(5, 2))
print(lp_div(10, 0))
print(lp_div(10, 1))

for i = 1, #log do
    print()
    local entry = log[i]
    print(string.format("Вызов #%d", i))
    print(string.format("Дата:    %s", entry.date))
    print(string.format("Параметры: %s", table.concat(entry.params, ", ")))
    print(string.format("Результат: %s", entry.result))
    print()
end