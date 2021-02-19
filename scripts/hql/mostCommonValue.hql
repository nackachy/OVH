select
    distinct(keyValueRankData.key) as key,
    keyValueRankData.value as value
 from
 (
     select

        key,value,
        rank() over(order by key,value) as rank

     from inputData inputData
     order by key,value
 )keyValueRankData

 join(
      select
            min(rank) as minRank,
            key
      from(
          select

              key,
              value,
              rank() over(order by key,value) as rank

          from inputData
          order by key,value
      ) keyValueRankData
      group by key
 ) rankedTable
 on keyValueRankData.rank = rankedTable.minRank