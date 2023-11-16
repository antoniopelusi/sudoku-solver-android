package com.antoniopelusi.sudokusolver

import android.util.Log

class Solver {

    private fun check(board: Array<IntArray>, row: Int, column: Int, num: Int): Boolean
    {
        for(y in 0 until 9)
        {
            if(board[row][y] == num)
            {
                return false
            }
        }

        for(x in 0 until 9)
        {
            if(board[x][column] == num)
            {
                return false
            }
        }

        val startRow: Int = row - row % 3
        val startCol: Int = column - column % 3

        for(x in 0 until 3)
        {
            for(y in 0 until 3)
            {
                if(board[x + startRow][y + startCol] == num)
                {
                    return false
                }
            }
        }
        return true
    }

    fun solve(board: Array<IntArray>, _row: Int, _column: Int): Boolean
    {
        var row = _row
        var column = _column

        if((row == 9 - 1) && (column == 9))
        {
            return true
        }

        if(column == 9)
        {
            row += 1
            column = 0
        }

        if(board[row][column] > 0)
        {
            return solve(board, row, column + 1)
        }

        for(num in 1 until 9 + 1)
        {
            if(check(board, row, column, num))
            {
                board[row][column] = num

                if(solve(board, row, column + 1))
                {
                    return true
                }
            }

            board[row][column] = 0
        }

        return false
    }

    fun checkBoard(board: Array<IntArray>): Boolean
    {
        //check for empty board
        var count = 0
        for(i in 0 until 9)
        {
            for (j in 0 until 9)
            {
                if(board[i][j] == 0)
                {
                    count++
                }
            }
        }
        if(count == 81)
        {
            return false
        }

        //check for no solution
        for(i in 0 until 9)
        {
            for (j in 0 until 9)
            {
                if(board[i][j] != 0)
                {
                    count = 0

                    for(num in 1 until 9 + 1)
                    {
                        //Log.v("i=$i j=$j num=$num", check(board, i, j, num).toString())

                        if(!check(board, i, j, num))
                        {
                            count++
                        }
                        if(count > 9)
                        {
                            return false
                        }
                    }
                }
            }
        }


        return true
    }
}