package com.exam.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exam.board.dto.BoardDto;

@Mapper
public interface BoardDao {

	public List<BoardDto> boardListDao(); // 목록

	public BoardDto boardViewDao(int num); // 내용보기

	public int boardWriteDao(String writer, String title, String content); // 글저장

	public int boardDeleteDao(int num); // 글삭제
	
	public List<BoardDto> boardSearch(String title); // 내용보기

}
