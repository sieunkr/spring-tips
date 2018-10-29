package com.example.demo;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			// Create a minioClient with the Minio Server name, Port, Access key and Secret key.
			MinioClient minioClient = new MinioClient("http://minio-ip", "accessKey", "secretKey");

			// Check if the bucket already exists.
			boolean isExist = minioClient.bucketExists("new_bucket");
			if(isExist) {
				System.out.println("Bucket already exists.");
			} else {
				// Make a new bucket called asiatrip to hold a zip file of photos.
				minioClient.makeBucket("new_bucket");
			}

			// Upload the zip file to the bucket with putObject
			minioClient.putObject("new_bucket","myimage.png", "D:\\\\Data\\\\myimage.png");
		} catch(MinioException e) {
			System.out.println("Error occurred: " + e);
		}
	}
}
