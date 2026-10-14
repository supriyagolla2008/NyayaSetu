import React from 'react';

export const StatCard = ({ children, className = '' }: { children?: React.ReactNode, className?: string }) => {
  return <div className={`statcard ${className}`}>{children}</div>;
};
